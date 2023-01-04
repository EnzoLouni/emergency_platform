package com.projet.pimpon.emergency.mapper;

import com.projet.pimpon.emergency.dto.AccidentDto;
import com.projet.pimpon.emergency.model.Accident;
import org.mapstruct.Mapper;

import static org.mapstruct.InjectionStrategy.FIELD;

@Mapper(injectionStrategy = FIELD, componentModel = "spring")
public interface AccidentMapper {
    AccidentDto toAccidentDto(Accident accident);
    Accident toAccident(AccidentDto accidentDto);
}
