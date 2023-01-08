package com.projet.pimpon.emergency.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class AgentUtils {
    /*public static List<AgentDto> findAllByStation(AgentRepository agentRepository, AgentMapper agentMapper, Station station) {
        return agentRepository.findAllByStation(station)
                .stream()
                .map(agentMapper::toAgentDto)
                .collect(toList());
    }

    public static List<AgentDto> findAllByTeam(AgentRepository agentRepository, AgentMapper agentMapper, Team team) {
        return agentRepository.findAllByTeam(team)
                .stream()
                .map(agentMapper::toAgentDto)
                .collect(toList());
    }*/
}
