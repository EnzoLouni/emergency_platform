package com.projet.pimpon.emergency.mapper;

import com.projet.pimpon.emergency.dtos.dto.AccidentDto;
import com.projet.pimpon.emergency.dtos.dtoapi.AccidentDtoApi;
import com.projet.pimpon.emergency.model.Accident;
import com.projet.pimpon.emergency.utils.PGpointUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.InjectionStrategy.FIELD;

@Mapper(injectionStrategy = FIELD, componentModel = "spring", imports = {PGpointUtils.class})
public interface AccidentMapper {
    @Mapping(target = "id", source = "accident.accidentId")
    @Mapping(target = "intensity", source = "accident.accidentIntensity")
    @Mapping(target = "teamId", source = "accident.teamId")
    @Mapping(target = "status", source = "accident.accidentStatus")
    @Mapping(target = "coordinates", expression = "java(PGpointUtils.toPGpoint(accident.getAccidentCoordinates()))")
    AccidentDto toAccidentDto(Accident accident);
    @Mapping(target = "id", source = "accident.accidentId")
    @Mapping(target = "intensity", source = "accident.accidentIntensity")
    @Mapping(target = "status", source = "accident.accidentStatus")
    @Mapping(target = "coordinates", source = "accident.accidentCoordinates")
    AccidentDtoApi toAccidentDtoApi(Accident accident);
    @Mapping(target = "accidentId", source = "accidentDto.id")
    @Mapping(target = "accidentIntensity", source = "accidentDto.intensity")
    @Mapping(target = "teamId", source = "accidentDto.teamId")
    @Mapping(target = "accidentStatus", source = "accidentDto.status")
    @Mapping(target = "accidentCoordinates", expression = "java(accidentDto.getCoordinates().getValue())")
    Accident toAccident(AccidentDto accidentDto);
}
