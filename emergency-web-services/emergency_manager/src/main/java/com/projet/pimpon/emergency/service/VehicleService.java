package com.projet.pimpon.emergency.service;

import com.projet.pimpon.emergency.dao.VehicleRepository;
import com.projet.pimpon.emergency.dtos.dto.StationDto;
import com.projet.pimpon.emergency.dtos.dto.VehicleDto;
import com.projet.pimpon.emergency.mapper.VehicleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Validated
@Slf4j
@Service
@RequiredArgsConstructor
public class VehicleService{
    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    public List<VehicleDto> mostRelevantVehiclesIn(StationDto station, Integer limit) {
        List<VehicleDto> vehicles = vehicleRepository.findAllByTeamIdIsNull()
                .stream()
                .map(vehicleMapper::toVehicleDto)
                .collect(toList());
        vehicles = vehicles.stream().filter(vehicleDto -> station.getVehicles().contains(vehicleDto)).collect(toList());
        Collections.sort(vehicles);
        return vehicles.subList(0, limit);
    }
}
