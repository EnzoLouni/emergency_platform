package com.projet.pimpon.emergency.dto;

import com.projet.pimpon.emergency.model.Coordinates;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StationDto {
    private String fullName;
    private List<VehicleDto> vehicles;
    private List<AgentDto> agents;
    private Coordinates coordinates;
}
