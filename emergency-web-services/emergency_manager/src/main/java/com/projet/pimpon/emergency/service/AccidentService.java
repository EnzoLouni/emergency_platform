package com.projet.pimpon.emergency.service;

import com.projet.pimpon.emergency.dao.AccidentRepository;
import com.projet.pimpon.emergency.dtos.dto.AccidentDto;
import com.projet.pimpon.emergency.dtos.dto.SensorDto;
import com.projet.pimpon.emergency.mapper.AccidentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Validated
@Slf4j
@Service
@RequiredArgsConstructor
public class AccidentService {

    private final AccidentRepository accidentRepository;

    private final AccidentMapper accidentMapper;

    private final SensorService sensorService;

    public void registerAccident(String sensors) {
        List<SensorDto> sensorsConcerned = sensorService.getSensorsTriggered(sensors);
        String[] sensorsData = sensors.split("[)],");
        List<Integer> intensities = new ArrayList<>();
        for (String sensorData: sensorsData) {
            Integer intensity = Integer.parseInt(sensorData.substring(sensorData.lastIndexOf(',')+1, sensorData.lastIndexOf(')') != -1 ? sensorData.lastIndexOf(')') : sensorData.length()));
            intensities.add(intensity);
        }

        AccidentDto accidentDto = new AccidentDto();
        //accidentRepository.save(accidentMapper.toAccident(accidentDto));
    }
}
