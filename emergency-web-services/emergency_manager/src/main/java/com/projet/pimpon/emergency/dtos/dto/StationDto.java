package com.projet.pimpon.emergency.dtos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.postgresql.geometric.PGpoint;

import java.util.List;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StationDto {
    private Integer id;
    private String name;
    private PGpoint coordinates;
    private List<AgentDto> agents;
    private List<VehicleDto> vehicles;
}
