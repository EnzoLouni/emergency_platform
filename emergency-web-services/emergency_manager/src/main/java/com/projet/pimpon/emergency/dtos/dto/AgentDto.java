package com.projet.pimpon.emergency.dtos.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.projet.pimpon.emergency.controller.views.Views;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgentDto {
    private Integer id;
    @JsonView({Views.ContextTeamView.class, Views.ContextStationView.class})
    private String name;
    @JsonView({Views.ContextTeamView.class, Views.ContextStationView.class})
    private Integer exhaustion;
    private Integer quality;
}
