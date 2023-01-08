package com.projet.pimpon.emergency.service;

import com.projet.pimpon.emergency.dao.SensorRepository;
import com.projet.pimpon.emergency.dtos.dto.SensorDto;
import com.projet.pimpon.emergency.mapper.SensorMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.geometric.PGpoint;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Validated
@Slf4j
@Service
@RequiredArgsConstructor
public class SensorService {
    private final SensorRepository sensorRepository;
    private final SensorMapper sensorMapper;
    public List<SensorDto> getSensorsTriggered(String sensors) {
        String[] sensorsData = sensors.split("[)],");
        List<SensorDto> sensorDtos = new ArrayList<>();
        for (String sensorData: sensorsData) {
            PGpoint pGpoint = new PGpoint();
            pGpoint.x = Double.parseDouble(sensorData.substring(1,sensorData.indexOf(',')));
            pGpoint.y = Double.parseDouble(sensorData.substring(sensorData.indexOf(',')+1,sensorData.lastIndexOf(',')));
            sensorDtos.add(sensorMapper.toSensorDto(sensorRepository.findBySensorCoordinates(pGpoint.getValue())));
        }
        return sensorDtos;
    }
}
