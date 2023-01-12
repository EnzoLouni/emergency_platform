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
        Integer longitude = 0;
        for(SensorDto sensorDto: sensors) {
            if(!YCoordinate.equals(sensorDto.getCoordinates().y)) {
                break;
            }
            longitude++;
        }
        Integer latitude = sensors.size()/longitude;
        for(int i = 0; i < latitude; ++i) {
            multiArraySensors.add(sensors.subList(i*longitude, (i+1)*longitude));
        }
        return multiArraySensors;
    }
}
