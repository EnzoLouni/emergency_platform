package com.projet.pimpon.emergency.service;

import com.projet.pimpon.emergency.dao.AgentRepository;
import com.projet.pimpon.emergency.dtos.dto.AgentDto;
import com.projet.pimpon.emergency.dtos.dto.StationDto;
import com.projet.pimpon.emergency.dtos.dto.VehicleDto;
import com.projet.pimpon.emergency.mapper.AgentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Comparator;
import java.util.List;

@Validated
@Slf4j
@Service
@RequiredArgsConstructor
public class AgentService{
    private final AgentRepository agentRepository;

    private final AgentMapper agentMapper;

    public List<AgentDto> mostRelevantAgentsIn(StationDto station, VehicleDto vehicle) {
        List<AgentDto> agents = station.getAgents();
        Integer vehicleCapacity = vehicle.getCapacity();
        Comparator<AgentDto> comparator = Comparator.comparingInt(AgentDto::getQuality);
        agents.sort(comparator);
        agents.subList(0, vehicleCapacity);
        return agents;
    }
}
