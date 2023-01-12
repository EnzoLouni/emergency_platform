package com.projet.pimpon.emergency.utils;

import com.projet.pimpon.emergency.dtos.dto.SensorDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Component
public class SensorUtils {
    public static List<List<SensorDto>> toMultipleArray(List<SensorDto> sensors) {
        List<List<SensorDto>> multiArraySensors = new ArrayList<>();
        Double YCoordinate = sensors.get(0).getCoordinates().y;
        Integer latitude = 0;
        for(SensorDto sensorDto: sensors) {
            if(!YCoordinate.equals(sensorDto.getCoordinates().y)) {
                break;
            }
            latitude++;
        }
        Integer longitude = sensors.size()/latitude;
        for(int i = 0; i < latitude; ++i) {
            multiArraySensors.add(sensors.subList(i*longitude, (i+1)*longitude));
        }
        return multiArraySensors;
    }
}
