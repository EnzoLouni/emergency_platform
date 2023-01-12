package com.projet.pimpon.emergency.mapper;

import com.projet.pimpon.emergency.dao.EquipmentRepository;
import com.projet.pimpon.emergency.dao.StationRepository;
import com.projet.pimpon.emergency.dtos.dto.VehicleDto;
import com.projet.pimpon.emergency.model.Vehicle;
import com.projet.pimpon.emergency.utils.PGpointUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

import static org.mapstruct.InjectionStrategy.FIELD;

@Mapper(injectionStrategy = FIELD, componentModel = "spring", imports = {Collectors.class, PGpointUtils.class})
public abstract class VehicleMapper {

    @Autowired
    protected StationRepository stationRepository;

    @Autowired
    protected EquipmentRepository equipmentRepository;

    @Autowired
    protected EquipmentMapper equipmentMapper;

    @Mapping(target = "id", source = "vehicle.vehicleId")
    @Mapping(target = "capacity", source = "vehicle.vehicleCapacity")
    @Mapping(target = "tankCapacity", source = "vehicle.vehicleTankCapacity")
    @Mapping(target = "isHeavy", source = "vehicle.vehicleIsheavyweight")
    @Mapping(target = "quality", source = "vehicle.vehicleQuality")
    @Mapping(target = "equipments", expression = "java(equipmentRepository.findAllByVehicleId(vehicle.getVehicleId()).stream().map(equipmentMapper::toEquipmentDto).collect(Collectors.toList()))")
    public abstract VehicleDto toVehicleDto(Vehicle vehicle);
    @Mapping(target = "vehicleId", source = "vehicleDto.id")
    @Mapping(target = "vehicleCapacity", source = "vehicleDto.capacity")
    @Mapping(target = "vehicleTankCapacity", source = "vehicleDto.tankCapacity")
    @Mapping(target = "vehicleIsheavyweight", source = "vehicleDto.isHeavy")
    @Mapping(target = "vehicleQuality", source = "vehicleDto.quality")
    public abstract Vehicle toVehicle(VehicleDto vehicleDto);
}
