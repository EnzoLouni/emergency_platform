package com.projet.pimpon.emergency.service;

import com.projet.pimpon.emergency.controller.gateway.MqttGateway;
import com.projet.pimpon.emergency.dao.AccidentRepository;
import com.projet.pimpon.emergency.dao.AgentRepository;
import com.projet.pimpon.emergency.dao.TeamRepository;
import com.projet.pimpon.emergency.dao.VehicleRepository;
import com.projet.pimpon.emergency.dtos.dto.AccidentDto;
import com.projet.pimpon.emergency.dtos.dto.AgentDto;
import com.projet.pimpon.emergency.dtos.dto.StationDto;
import com.projet.pimpon.emergency.dtos.dto.TeamDto;
import com.projet.pimpon.emergency.dtos.dto.VehicleDto;
import com.projet.pimpon.emergency.dtos.dtoapi.TeamDtoApi;
import com.projet.pimpon.emergency.mapper.AccidentMapper;
import com.projet.pimpon.emergency.mapper.AgentMapper;
import com.projet.pimpon.emergency.mapper.TeamMapper;
import com.projet.pimpon.emergency.mapper.VehicleMapper;
import com.projet.pimpon.emergency.model.Accident;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

import static com.projet.pimpon.emergency.config.MqttConfig.EMERGENCY_SIMULATOR;
import static java.util.stream.Collectors.toList;

@Validated
@Slf4j
@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final AccidentRepository accidentRepository;
    private static final Integer DELIMITER_N_VEHICLES = 6;
    private static final Integer MINIMUM_AGENTS_PER_VEHICLE = 3;
    private final StationService stationService;
    private final VehicleService vehicleService;
    private final AgentService agentService;
    private final TeamMapper teamMapper;
    private final AccidentMapper accidentMapper;
    private final AgentRepository agentRepository;
    private final VehicleRepository vehicleRepository;
    private final MqttGateway mqttGateway;
    private final AgentMapper agentMapper;
    private final VehicleMapper vehicleMapper;

    private static Integer sumOfQualifications(List<AgentDto> agents, VehicleDto vehicle) {
        Integer qualitySum = 0;
        for(AgentDto agent: agents) {
            qualitySum += agent.getQuality();
        }
        return vehicle.getQuality() * qualitySum;
    }

    public List<TeamDtoApi> getAllTeams() {
        return teamRepository.findAll()
                .stream()
                .map(teamMapper::toTeamDtoApi)
                .collect(toList());
    }

    private List<TeamDto> createTeams(List<AccidentDto> accidentToCreate) {
        List<TeamDto> teamCreated = new ArrayList<>();
        for(AccidentDto accidentDto: accidentToCreate) {
            Integer globalQuality = 0;
            StationDto station = stationService.mostRelevantStationFor(accidentDto);
            List<VehicleDto> vehicles = vehicleService.mostRelevantVehiclesIn(station, accidentDto.getIntensity() > DELIMITER_N_VEHICLES ? 2 : 1);
            Integer teamCapacity = 0;
            for(VehicleDto vehicle: vehicles) {
                teamCapacity += vehicle.getCapacity();
            }
            List<AgentDto> relevantAgents = agentService.mostRelevantAgentsIn(station, teamCapacity);
            Integer capacityOfPreviousVehicles = 0;
            if(teamCapacity.equals(relevantAgents.size())) {
                for(VehicleDto vehicle: vehicles) {
                    globalQuality += sumOfQualifications(relevantAgents.subList(capacityOfPreviousVehicles, capacityOfPreviousVehicles + vehicle.getCapacity()), vehicle);
                    capacityOfPreviousVehicles += vehicle.getCapacity();
                }
            }
            else if(teamCapacity > relevantAgents.size() && relevantAgents.size() >= MINIMUM_AGENTS_PER_VEHICLE) {
                List<AgentDto> group1 = new ArrayList<>();
                List<AgentDto> group2 = new ArrayList<>();
                for(int i = 0; i < relevantAgents.size(); ++i) {
                    if(i%2 == 0)
                        group1.add(relevantAgents.get(i));
                    else
                        group2.add(relevantAgents.get(i));
                }
                Integer vehiculeIndice = 0;
                for(VehicleDto vehicle: vehicles) {
                    if(vehiculeIndice%2 == 0) {
                        globalQuality += sumOfQualifications(relevantAgents.subList(capacityOfPreviousVehicles, capacityOfPreviousVehicles + group1.size()), vehicle);
                        capacityOfPreviousVehicles += group1.size();
                    }
                    else {
                        globalQuality += sumOfQualifications(relevantAgents.subList(capacityOfPreviousVehicles, capacityOfPreviousVehicles + group2.size()), vehicle);
                        capacityOfPreviousVehicles += group2.size();
                    }
                    vehiculeIndice++;
                }
            }
            else {
                vehicles = null;
                relevantAgents = null;
            }
            if(vehicles != null && relevantAgents != null) {
                TeamDto newTeam = new TeamDto(null, accidentDto, relevantAgents, vehicles, globalQuality);
                TeamDto teamRegistered = teamMapper.toTeamDto(teamRepository.save(teamMapper.toTeam(newTeam)));
                accidentDto.setTeamId(teamRegistered.getId());
                Accident accident = accidentMapper.toAccident(accidentDto);
  //              accidentRepository.saveAccident(accident.getAccidentIntensity(), accident.getTeamId(), accident.getAccidentStatus().toString(), accident.getAccidentCoordinates());
                vehicleRepository.saveAll(vehicles.stream().map(vehicleMapper::toVehicle).collect(toList()));
                agentRepository.saveAll(relevantAgents.stream().map(agentMapper::toAgent).collect(toList()));
            }
            else {
                Accident accident = accidentMapper.toAccident(accidentDto);
                accidentRepository.save(accidentMapper.toAccident(accidentDto));
            }
        }
        return teamCreated;
    }

    public void manage(List<AccidentDto> accidentsToCreate, List<AccidentDto> accidentToUpdate){
        List<TeamDto> teamsToSend = createTeams(accidentsToCreate);
        for(TeamDto teamDto: teamsToSend) {
            mqttGateway.sendToMqtt(EMERGENCY_SIMULATOR, teamDto);
        }
    }

}
