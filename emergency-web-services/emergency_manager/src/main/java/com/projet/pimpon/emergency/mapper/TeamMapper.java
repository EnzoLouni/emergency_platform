package com.projet.pimpon.emergency.mapper;

import com.projet.pimpon.emergency.dto.TeamDto;
import com.projet.pimpon.emergency.model.Team;
import org.mapstruct.Mapper;

import static org.mapstruct.InjectionStrategy.FIELD;

@Mapper(injectionStrategy = FIELD, componentModel = "spring")
public interface TeamMapper {
    TeamDto toTeamDto(Team team);
    Team toTeam(TeamDto teamDto);
}
