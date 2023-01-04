package com.projet.pimpon.emergency.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamDto {
    private List<AccidentDto> accidents;
    private List<AgentDto> agents;
    private List<VehicleDto> vehicles;
    private List<StationDto> stations;
    private Integer quality;
}
