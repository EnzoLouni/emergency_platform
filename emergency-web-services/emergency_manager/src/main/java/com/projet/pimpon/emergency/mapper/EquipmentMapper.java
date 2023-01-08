package com.projet.pimpon.emergency.mapper;

import com.projet.pimpon.emergency.dtos.dto.EquipmentDto;
import com.projet.pimpon.emergency.model.Equipment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.InjectionStrategy.FIELD;

@Mapper(injectionStrategy = FIELD, componentModel = "spring")
public interface EquipmentMapper {
    @Mapping(target = "id", source = "equipment.equipmentId")
    @Mapping(target = "type", source = "equipment.equipmentType")
    @Mapping(target = "features", source = "equipment.equipmentFeatures")
    @Mapping(target = "quality", source = "equipment.equipmentQuality")
    EquipmentDto toEquipmentDto(Equipment equipment);
    @Mapping(target = "equipmentId", source = "equipmentDto.id")
    @Mapping(target = "equipmentType", source = "equipmentDto.type")
    @Mapping(target = "equipmentFeatures", source = "equipmentDto.features")
    @Mapping(target = "equipmentQuality", source = "equipmentDto.quality")
    Equipment toEquipment(EquipmentDto equipmentDto);
}
