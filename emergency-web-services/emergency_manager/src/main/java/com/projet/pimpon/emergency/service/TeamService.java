package com.projet.pimpon.emergency.service;

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
import com.projet.pimpon.emergency.model.AccidentStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static java.util.Collections.singletonList;
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

    public static Integer sumOfQualifications(List<AgentDto> agents, List<VehicleDto> vehicles) {
        Integer qualitySum = 0;
        for(AgentDto agent: agents) {
            qualitySum += agent.getQuality();
        }
        for(VehicleDto vehicle: vehicles) {
            qualitySum += vehicle.getQuality();
        }
        return qualitySum;
    }

    public List<TeamDtoApi> getAllTeams() {
        return teamRepository.findAll()
                .stream()
                .map(teamMapper::toTeamDtoApi)
                .collect(toList());
    }

    public void createTeam(AccidentDto accident) {
        StationDto relevantStation = stationService.mostRelevantStationFor(accident);
        VehicleDto relevantVehicle = vehicleService.mostRelevantVehicleIn(relevantStation);
        List<AgentDto> relevantAgents = agentService.mostRelevantAgentsIn(relevantStation, relevantVehicle);
        TeamDto team = TeamDto.builder()
                .accident(accident)//.station(relevantStation)
                .vehicles(singletonList(relevantVehicle))
                .agents(relevantAgents)
                .quality(sumOfQualifications(relevantAgents, singletonList(relevantVehicle)))
                .build();
        accident.setStatus(AccidentStatus.PROCESSING);
        accidentRepository.save(accidentMapper.toAccident(accident));
        teamRepository.save(teamMapper.toTeam(team));
    }

}
