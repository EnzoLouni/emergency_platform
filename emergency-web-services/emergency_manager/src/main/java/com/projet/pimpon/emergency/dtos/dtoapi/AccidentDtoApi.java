package com.projet.pimpon.emergency.dtos.dtoapi;

import com.fasterxml.jackson.annotation.JsonView;
import com.projet.pimpon.emergency.controller.views.Views;
import com.projet.pimpon.emergency.model.AccidentStatus;
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
public class AccidentDtoApi {
    private Integer id;
    @JsonView({Views.ContextTeamView.class})
    private Integer intensity;
    @JsonView({Views.ContextTeamView.class})
    private AccidentStatus status;
    @JsonView({Views.ContextTeamView.class})
    private String coordinates;
}
