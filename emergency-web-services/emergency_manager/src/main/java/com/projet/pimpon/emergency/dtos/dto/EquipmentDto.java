package com.projet.pimpon.emergency.dtos.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.projet.pimpon.emergency.controller.views.Views;
import com.projet.pimpon.emergency.model.EquipmentType;
import com.projet.pimpon.emergency.model.Feature;
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
public class EquipmentDto {
    private Integer id;
    @JsonView({Views.ContextTeamView.class})
    private EquipmentType type;
    @JsonView({Views.ContextTeamView.class})
    private Feature features;
    private Integer quality;
}
