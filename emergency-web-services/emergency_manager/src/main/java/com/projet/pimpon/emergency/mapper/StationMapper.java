package com.projet.pimpon.emergency.mapper;

import com.projet.pimpon.emergency.dto.StationDto;
import com.projet.pimpon.emergency.model.Station;
import org.mapstruct.Mapper;

import static org.mapstruct.InjectionStrategy.FIELD;

@Mapper(injectionStrategy = FIELD, componentModel = "spring")
public interface StationMapper {
    StationDto toStationDto(Station station);
    Station toStation(StationDto station);
}
