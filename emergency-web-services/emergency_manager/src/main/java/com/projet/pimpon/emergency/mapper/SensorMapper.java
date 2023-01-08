package com.projet.pimpon.emergency.mapper;

import com.projet.pimpon.emergency.dtos.dto.SensorDto;
import com.projet.pimpon.emergency.model.Sensor;
import com.projet.pimpon.emergency.utils.PGpointUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.InjectionStrategy.FIELD;

@Mapper(injectionStrategy = FIELD, componentModel = "spring", imports = {PGpointUtils.class})
public interface SensorMapper {
    @Mapping(target = "id", source = "sensor.sensorId")
    @Mapping(target = "coordinates", expression = "java(PGpointUtils.toPGpoint(sensor.getSensorCoordinates()))")
    @Mapping(target = "status", source = "sensor.sensorStatus")
    SensorDto toSensorDto(Sensor sensor);
    @Mapping(target = "sensorId", source = "sensorDto.id")
    @Mapping(target = "sensorCoordinates", expression = "java(sensorDto.getCoordinates().getValue())")
    @Mapping(target = "sensorStatus", source = "sensorDto.status")
    Sensor toSensor(SensorDto sensorDto);
}
