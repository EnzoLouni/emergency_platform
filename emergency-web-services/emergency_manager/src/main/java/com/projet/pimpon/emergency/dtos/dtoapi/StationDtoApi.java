package com.projet.pimpon.emergency.dtos.dtoapi;

import com.fasterxml.jackson.annotation.JsonView;
import com.projet.pimpon.emergency.controller.views.Views;
import com.projet.pimpon.emergency.dtos.dto.AgentDto;
import com.projet.pimpon.emergency.dtos.dto.VehicleDto;
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
public class StationDtoApi {
    private Integer id;
    @JsonView({Views.ContextStationView.class})
    private String name;
    @JsonView({Views.ContextStationView.class})
    private String coordinates;
    @JsonView({Views.ContextStationView.class})
    private List<AgentDto> agents;
    @JsonView({Views.ContextStationView.class})
    private List<VehicleDto> vehicles;
}
