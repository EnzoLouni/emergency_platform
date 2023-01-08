package com.projet.pimpon.emergency.mapper;

import com.projet.pimpon.emergency.dao.AccidentRepository;
import com.projet.pimpon.emergency.dao.AgentRepository;
import com.projet.pimpon.emergency.dao.VehicleRepository;
import com.projet.pimpon.emergency.dtos.dto.TeamDto;
import com.projet.pimpon.emergency.dtos.dtoapi.TeamDtoApi;
import com.projet.pimpon.emergency.model.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

import static org.mapstruct.InjectionStrategy.FIELD;

@Mapper(injectionStrategy = FIELD, componentModel = "spring", imports = {Collectors.class})
public abstract class TeamMapper {

    @Autowired
    protected AccidentRepository accidentRepository;

    @Autowired
    protected AccidentMapper accidentMapper;

    @Autowired
    protected AgentRepository agentRepository;

    @Autowired
    protected AgentMapper agentMapper;

    @Autowired
    protected VehicleRepository vehicleRepository;

    @Autowired
    protected VehicleMapper vehicleMapper;
    @Mapping(target = "id", source = "team.teamId")
    @Mapping(target = "accident", expression = "java(accidentMapper.toAccidentDto(accidentRepository.findByTeamId(team.getTeamId())))")
    @Mapping(target = "agents", expression = "java(agentRepository.findAllByTeamId(team.getTeamId()).stream().map(agentMapper::toAgentDto).collect(Collectors.toList()))")
    @Mapping(target = "vehicles", expression = "java(vehicleRepository.findAllByTeamId(team.getTeamId()).stream().map(vehicleMapper::toVehicleDto).collect(Collectors.toList()))")
    @Mapping(target = "quality", source = "team.teamQuality")
    public abstract TeamDto toTeamDto(Team team);

    @Mapping(target = "id", source = "team.teamId")
    @Mapping(target = "accident", expression = "java(accidentMapper.toAccidentDtoApi(accidentRepository.findByTeamId(team.getTeamId())))")
    @Mapping(target = "agents", expression = "java(agentRepository.findAllByTeamId(team.getTeamId()).stream().map(agentMapper::toAgentDto).collect(Collectors.toList()))")
    @Mapping(target = "vehicles", expression = "java(vehicleRepository.findAllByTeamId(team.getTeamId()).stream().map(vehicleMapper::toVehicleDto).collect(Collectors.toList()))")
    @Mapping(target = "quality", source = "team.teamQuality")
    public abstract TeamDtoApi toTeamDtoApi(Team team);

    @Mapping(target = "teamId", source = "teamDto.id")
    @Mapping(target = "teamQuality", source = "teamDto.quality")
    public abstract Team toTeam(TeamDto teamDto);
}
