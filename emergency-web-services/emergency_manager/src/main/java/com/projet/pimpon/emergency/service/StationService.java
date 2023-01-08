package com.projet.pimpon.emergency.service;

import com.projet.pimpon.emergency.dao.StationRepository;
import com.projet.pimpon.emergency.dtos.dto.AccidentDto;
import com.projet.pimpon.emergency.dtos.dto.StationDto;
import com.projet.pimpon.emergency.dtos.dtoapi.StationDtoApi;
import com.projet.pimpon.emergency.mapper.StationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.geometric.PGpoint;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static com.projet.pimpon.emergency.utils.PGpointUtils.distance;
import static java.util.stream.Collectors.toList;

@Validated
@Slf4j
@Service
@RequiredArgsConstructor
public class StationService {
    private final StationRepository stationRepository;

    private final StationMapper stationMapper;

    public List<StationDtoApi> getAllStations() {
        return stationRepository.findAll()
                .stream()
                .map(stationMapper::toStationDtoApi)
                .collect(toList());
    }

    public StationDto mostRelevantStationFor(AccidentDto accident) {
        List<StationDto> stationDtos = stationRepository.findAll().stream()
                .map(stationMapper::toStationDto)
                .collect(toList());
        StationDto mostRelevantStation = stationDtos.get(0);
        PGpoint accidentCoordinates = accident.getCoordinates();
        Double mostRelevantDistance = distance(mostRelevantStation.getCoordinates(), accidentCoordinates);
        for(StationDto s: stationDtos) {
            Double distance = distance(s.getCoordinates(), accidentCoordinates);
            if(distance < mostRelevantDistance) {
                mostRelevantDistance = distance;
                mostRelevantStation = s;
            }
        }
        return mostRelevantStation;
    }
}
