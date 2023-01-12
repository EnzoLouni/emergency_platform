package com.projet.pimpon.emergency.service;

import com.projet.pimpon.emergency.dao.AccidentRepository;
import com.projet.pimpon.emergency.dtos.dto.AccidentDto;
import com.projet.pimpon.emergency.dtos.dto.SensorDto;
import com.projet.pimpon.emergency.dtos.dto.Square;
import com.projet.pimpon.emergency.mapper.AccidentMapper;
import com.projet.pimpon.emergency.model.Accident;
import com.projet.pimpon.emergency.model.AccidentStatus;
import com.projet.pimpon.emergency.utils.SensorUtils;
import com.projet.pimpon.emergency.utils.SquareUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.geometric.PGpoint;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Validated
@Slf4j
@Service
@RequiredArgsConstructor
public class AccidentService {

    private final AccidentRepository accidentRepository;

    private final AccidentMapper accidentMapper;

    private final SensorService sensorService;

    private final TeamService teamService;

    private List<Integer> getIntensitiesDetected(List<String> sensorsData) {
        List<Integer> intensities = new ArrayList<>();
        for (String sensorData: sensorsData) {
            Integer intensityDetected = Integer.parseInt(sensorData.substring(sensorData.lastIndexOf(',')+1, sensorData.lastIndexOf(')') != -1 ? sensorData.lastIndexOf(')') : sensorData.length()));
            intensities.add(intensityDetected);
        }
        return intensities;
    }

    public void manageAlerts(String sensors) {
        List<String> sensorsData = Arrays.stream(sensors.split("[)]")).toList();
        List<SensorDto> sensorsTriggered = sensorService.getSensorsTriggered(sensorsData);
        List<List<SensorDto>> multiArraySensors = SensorUtils.toMultipleArray(sensorsTriggered);
        List<Integer> intensitiesDetected = getIntensitiesDetected(sensorsData);
        List<Square> squares = SquareUtils.toSquareList(multiArraySensors, intensitiesDetected);
        List<AccidentDto> accidents = accidentRepository.findAll()
                .stream()
                .map(accidentMapper::toAccidentDto)
                .collect(toList());
        List<AccidentDto> accidentsToCreate = new ArrayList<>();
        List<AccidentDto> accidentsToUpdate = new ArrayList<>();
        List<AccidentDto> accidentsToDelete = new ArrayList<>();
        for (AccidentDto accident : accidents) {
            PGpoint accidentLocation = accident.getCoordinates();
            squares.forEach(square -> {
                PGpoint centerSquare = square.getCenter();
                if (centerSquare.equals(accidentLocation)) {
                    if (square.getIntensity().equals(0)) {
                        accidentsToDelete.add(accident);
                    }
                    else {
                        accident.setIntensity(square.getIntensity());
                        accidentsToUpdate.add(accident);
                    }
                }
            });
        }
        squares = squares.stream().filter(square -> {
            PGpoint squareCenter = square.getCenter();
            Boolean isUpdated = accidentsToUpdate.stream().anyMatch(accidentDto -> accidentDto.getCoordinates().equals(squareCenter));
            Boolean isDeleted = accidentsToDelete.stream().anyMatch(accidentDto -> accidentDto.getCoordinates().equals(squareCenter));
            return !square.getIntensity().equals(0) && !isUpdated && !isDeleted;
        }).collect(toList());
        squares.forEach(square -> {
            accidentsToCreate.add(new AccidentDto(null, square.getIntensity(), null,AccidentStatus.NOT_TREATED, square.getCenter()));
        });
        teamService.manage(accidentsToCreate);
        accidentsToUpdate.forEach(accidentToCreate -> {
            Accident accident = accidentMapper.toAccident(accidentToCreate);
            accidentRepository.updateAccident(accident.getAccidentId(), accident.getAccidentIntensity(), accident.getTeamId(), accident.getAccidentStatus().toString(), accident.getAccidentCoordinates());
        });
        accidentRepository.deleteAllById(accidentsToDelete
                .stream()
                .map(AccidentDto::getId)
                .collect(toList()));
    }
}
