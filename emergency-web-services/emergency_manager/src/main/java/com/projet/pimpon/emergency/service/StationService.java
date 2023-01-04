package com.projet.pimpon.emergency.service;

import com.projet.pimpon.emergency.dao.StationRepository;
import com.projet.pimpon.emergency.dto.AccidentDto;
import com.projet.pimpon.emergency.dto.StationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Slf4j
@Service
@RequiredArgsConstructor
public class StationService {
    private final StationRepository stationRepository;

    //private final StationMapper stationMapper;

    public StationDto mostRelevantStationFor(AccidentDto accident) {
        /*List<StationDto> stationDtos = stationRepository.findAll().stream()
                .map(stationMapper::toStationDto)
                .collect(toList());
        StationDto mostRelevantStation = stationDtos.get(0);
        Coordinates accidentCoordinates = accident.getCoordinates();
        Double mostRelevantDistance = distance(accidentCoordinates, mostRelevantStation.getCoordinates());
        for(StationDto s: stationDtos) {
            Double distance = distance(accidentCoordinates, s.getCoordinates());
            if(distance < mostRelevantDistance) {
                mostRelevantDistance = distance;
                mostRelevantStation = s;
            }
        }
        return mostRelevantStation;*/
        return null;
    }
}
