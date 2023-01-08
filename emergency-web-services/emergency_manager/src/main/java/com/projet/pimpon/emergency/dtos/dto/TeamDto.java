package com.projet.pimpon.emergency.dtos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamDto {
    private Integer id;
    private AccidentDto accident;
    private List<AgentDto> agents;
    private List<VehicleDto> vehicles;
    private Integer quality;
}
