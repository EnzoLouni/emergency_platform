package com.projet.pimpon.emergency.mapper;

import com.projet.pimpon.emergency.dtos.dto.AgentDto;
import com.projet.pimpon.emergency.model.Agent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.InjectionStrategy.FIELD;

@Mapper(injectionStrategy = FIELD, componentModel = "spring")
public interface AgentMapper {
    @Mapping(target = "id", source = "agent.agentId")
    @Mapping(target = "name", source = "agent.agentName")
    @Mapping(target = "exhaustion", source = "agent.agentExhaustion")
    @Mapping(target = "quality", source = "agent.agentQuality")
    AgentDto toAgentDto(Agent agent);
    @Mapping(target = "agentId", source = "agentDto.id")
    @Mapping(target = "agentName", source = "agentDto.name")
    @Mapping(target = "agentExhaustion", source = "agentDto.exhaustion")
    @Mapping(target = "agentQuality", source = "agentDto.quality")
    Agent toAgent(AgentDto agentDto);
}
