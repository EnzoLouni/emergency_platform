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
public class TeamDtoApi {
    @JsonView({Views.ContextTeamView.class})
    private Integer id;
    @JsonView({Views.ContextTeamView.class})
    private AccidentDtoApi accident;
    @JsonView({Views.ContextTeamView.class})
    private List<AgentDto> agents;
    @JsonView({Views.ContextTeamView.class})
    private List<VehicleDto> vehicles;
    @JsonView({Views.ContextTeamView.class})
    private Integer quality;
}
