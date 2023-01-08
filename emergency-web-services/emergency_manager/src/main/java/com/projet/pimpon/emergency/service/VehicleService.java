package com.projet.pimpon.emergency.service;

import com.projet.pimpon.emergency.dao.VehicleRepository;
import com.projet.pimpon.emergency.dtos.dto.StationDto;
import com.projet.pimpon.emergency.dtos.dto.VehicleDto;
import com.projet.pimpon.emergency.mapper.VehicleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Slf4j
@Service
@RequiredArgsConstructor
public class VehicleService{
    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    public VehicleDto mostRelevantVehicleIn(StationDto station) {
        List<VehicleDto> vehicles = station.getVehicles();
        VehicleDto mostRelevantVehicle = vehicles.get(0);
        Integer quality = mostRelevantVehicle.getQuality();
        for(VehicleDto vehicle: vehicles) {
            if(quality < vehicle.getQuality()) {
                quality = vehicle.getQuality();
                mostRelevantVehicle = vehicle;
            }
        }
        return mostRelevantVehicle;
    }
}
