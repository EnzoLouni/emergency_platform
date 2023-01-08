package com.projet.pimpon.emergency.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class VehicleUtils {
    /*public static List<VehicleDto> findAllByStation(VehicleRepository vehicleRepository, VehicleMapper vehicleMapper, Station station) {
        return vehicleRepository.findAllByStation(station)
                .stream()
                .map(vehicleMapper::toVehicleDto)
                .collect(toList());
    }

    public static List<VehicleDto> findAllByTeam(VehicleRepository vehicleRepository, VehicleMapper vehicleMapper, Team team) {
        return vehicleRepository.findAllByTeam(team)
                .stream()
                .map(vehicleMapper::toVehicleDto)
                .collect(toList());
    }*/
}
