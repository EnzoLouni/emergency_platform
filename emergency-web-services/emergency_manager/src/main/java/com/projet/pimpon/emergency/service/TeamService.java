package com.projet.pimpon.emergency.service;

import com.projet.pimpon.emergency.dao.AccidentRepository;
import com.projet.pimpon.emergency.dao.TeamRepository;
import com.projet.pimpon.emergency.dto.AccidentDto;
import com.projet.pimpon.emergency.dto.AgentDto;
import com.projet.pimpon.emergency.dto.StationDto;
import com.projet.pimpon.emergency.dto.TeamDto;
import com.projet.pimpon.emergency.dto.VehicleDto;
import com.projet.pimpon.emergency.model.AccidentStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static java.util.Collections.singletonList;

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
    //private final TeamMapper teamMapper;
    //private final AccidentMapper accidentMapper;

    public static Integer sumOfQualifications(List<AgentDto> agents, List<VehicleDto> vehicles) {
        Integer qualitySum = 0;
        for(AgentDto a: agents) {
            qualitySum += a.getQuality();
        }
        for(VehicleDto v: vehicles) {
            qualitySum += v.getQuality();
        }
        return qualitySum;
    }

    public void createTeam(AccidentDto accident) {
        StationDto relevantStation = stationService.mostRelevantStationFor(accident);
        VehicleDto relevantVehicle = vehicleService.mostRelevantVehicleIn(relevantStation);
        List<AgentDto> relevantAgents = agentService.mostRelevantAgentsIn(relevantStation, relevantVehicle);
        TeamDto team = TeamDto.builder()
                .accidents(singletonList(accident))
                .stations(singletonList(relevantStation))
                .vehicles(singletonList(relevantVehicle))
                .agents(relevantAgents)
                .quality(sumOfQualifications(relevantAgents, singletonList(relevantVehicle)))
                .build();
        accident.setAccidentStatus(AccidentStatus.PROCESSING);
        //accidentRepository.save(accidentMapper.toAccident(accident));
        //teamRepository.save(teamMapper.toTeam(team));
    }

}
