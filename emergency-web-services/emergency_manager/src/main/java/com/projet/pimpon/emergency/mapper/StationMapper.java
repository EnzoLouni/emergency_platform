package com.projet.pimpon.emergency.mapper;

import com.projet.pimpon.emergency.dao.AgentRepository;
import com.projet.pimpon.emergency.dao.VehicleRepository;
import com.projet.pimpon.emergency.dtos.dto.StationDto;
import com.projet.pimpon.emergency.dtos.dtoapi.StationDtoApi;
import com.projet.pimpon.emergency.model.Station;
import com.projet.pimpon.emergency.utils.PGpointUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

import static org.mapstruct.InjectionStrategy.FIELD;


@Mapper(injectionStrategy = FIELD, componentModel = "spring", imports = {Collectors.class, PGpointUtils.class})
public abstract class StationMapper {

    @Autowired
    protected AgentRepository agentRepository;

    @Autowired
    protected AgentMapper agentMapper;

    @Autowired
    protected VehicleRepository vehicleRepository;

    @Autowired
    protected VehicleMapper vehicleMapper;

    @Mapping(target = "id", source = "station.stationId")
    @Mapping(target = "name", source = "station.stationName")
    @Mapping(target = "coordinates", expression = "java(PGpointUtils.toPGpoint(station.getStationCoordinates()))")
    @Mapping(target = "agents", expression = "java(agentRepository.findAllByStationId(station.getStationId()).stream().map(agentMapper::toAgentDto).collect(Collectors.toList()))")
    @Mapping(target = "vehicles", expression = "java(vehicleRepository.findAllByStationId(station.getStationId()).stream().map(vehicleMapper::toVehicleDto).collect(Collectors.toList()))")
    public abstract StationDto toStationDto(Station station);

    @Mapping(target = "id", source = "station.stationId")
    @Mapping(target = "name", source = "station.stationName")
    @Mapping(target = "coordinates", source = "station.stationCoordinates")
    @Mapping(target = "agents", expression = "java(agentRepository.findAllByStationIdAndTeamIdIsNull(station.getStationId()).stream().map(agentMapper::toAgentDto).collect(Collectors.toList()))")
    @Mapping(target = "vehicles", expression = "java(vehicleRepository.findAllByStationIdAndTeamIdIsNull(station.getStationId()).stream().map(vehicleMapper::toVehicleDto).collect(Collectors.toList()))")
    public abstract StationDtoApi toStationDtoApi(Station station);

    @Mapping(target = "stationId", source = "stationDto.id")
    @Mapping(target = "stationName", source = "stationDto.name")
    @Mapping(target = "stationCoordinates", expression = "java(stationDto.getCoordinates().getValue())")
    public abstract Station toStation(StationDto stationDto);
}
