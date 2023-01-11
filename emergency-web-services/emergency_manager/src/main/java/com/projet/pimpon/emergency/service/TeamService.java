package com.projet.pimpon.emergency.service;

import com.projet.pimpon.emergency.dao.AccidentRepository;
import com.projet.pimpon.emergency.dao.TeamRepository;
import com.projet.pimpon.emergency.dtos.dto.AccidentDto;
import com.projet.pimpon.emergency.dtos.dto.AgentDto;
import com.projet.pimpon.emergency.dtos.dto.VehicleDto;
import com.projet.pimpon.emergency.dtos.dtoapi.TeamDtoApi;
import com.projet.pimpon.emergency.mapper.AccidentMapper;
import com.projet.pimpon.emergency.mapper.TeamMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

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

    private void createTeams(List<AccidentDto> accidentToCreate) {
        /*for(AccidentDto accidentDto: accidentToCreate) {
            StationDto station = stationService.mostRelevantStationFor(accidentDto);
            List<VehicleDto> vehicles = vehicleService.mostRelevantVehiclesIn(station, 1);
            List<AgentDto> agents = new ArrayList<>();
            for(VehicleDto vehicle: vehicles) {
                List<AgentDto> agents
                agents.addAll(agentService.mostRelevantAgentsIn(station, vehicle));

            }
            TeamDto newTeam = new TeamDto(null, accidentDto, agents, vehicles, 0);
            teamRepository.save(teamMapper.toTeam(newTeam));
            Accident accident = accidentMapper.toAccident(accidentDto);
            accidentRepository.saveAccident(accident.getAccidentIntensity(), accident.getTeamId(), accident.getAccidentStatus(), accident.getAccidentCoordinates());
        }*/
    }

    public void manage(List<AccidentDto> accidentsToCreate, List<AccidentDto> accidentToUpdate){
        createTeams(accidentsToCreate);
    }

}
