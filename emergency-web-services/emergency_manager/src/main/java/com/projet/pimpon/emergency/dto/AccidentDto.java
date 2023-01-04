package com.projet.pimpon.emergency.dto;

import com.projet.pimpon.emergency.model.AccidentStatus;
import com.projet.pimpon.emergency.model.Coordinates;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccidentDto {
    private Integer intensity;
    private AccidentStatus accidentStatus;
    private Coordinates coordinates;
}
