package com.projet.pimpon.emergency.service;

import com.projet.pimpon.emergency.controller.gateway.MqttGateway;
import com.projet.pimpon.emergency.dao.AccidentRepository;
import com.projet.pimpon.emergency.dao.TeamRepository;
import com.projet.pimpon.emergency.dtos.dto.AccidentDto;
import com.projet.pimpon.emergency.dtos.dto.AgentDto;
import com.projet.pimpon.emergency.dtos.dto.StationDto;
import com.projet.pimpon.emergency.dtos.dto.TeamDto;
import com.projet.pimpon.emergency.dtos.dto.VehicleDto;
import com.projet.pimpon.emergency.dtos.dtoapi.TeamDtoApi;
import com.projet.pimpon.emergency.mapper.AccidentMapper;
import com.projet.pimpon.emergency.mapper.TeamMapper;
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
    private final StationService stationService;
    private final VehicleService vehicleService;
    private final AgentService agentService;
    private final TeamMapper teamMapper;
    private final AccidentMapper accidentMapper;
    private final MqttGateway mqttGateway;

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
            List<VehicleDto> vehicles = vehicleService.mostRelevantVehiclesIn(station, 1);
            List<AgentDto> agents = new ArrayList<>();
            for(VehicleDto vehicle: vehicles) {
                List<AgentDto> relevantAgents = agentService.mostRelevantAgentsIn(station, vehicle);
                agents.addAll(relevantAgents);
                globalQuality += sumOfQualifications(relevantAgents, vehicle);
            }
            TeamDto newTeam = new TeamDto(null, accidentDto, agents, vehicles, globalQuality);
            teamRepository.save(teamMapper.toTeam(newTeam));
            Accident accident = accidentMapper.toAccident(accidentDto);
            accidentRepository.saveAccident(accident.getAccidentIntensity(), accident.getTeamId(), accident.getAccidentStatus(), accident.getAccidentCoordinates());
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
