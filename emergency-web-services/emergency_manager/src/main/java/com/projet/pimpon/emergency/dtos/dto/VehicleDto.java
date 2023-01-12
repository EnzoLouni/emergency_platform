package com.projet.pimpon.emergency.dtos.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.projet.pimpon.emergency.controller.views.Views;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDto implements Comparable<VehicleDto>{
    @JsonView({Views.ContextTeamView.class, Views.ContextStationView.class})
    private Integer id;
    @JsonView({Views.ContextTeamView.class, Views.ContextStationView.class})
    private Integer capacity;
    @JsonView({Views.ContextTeamView.class, Views.ContextStationView.class})
    private Integer tankCapacity;
    @JsonView({Views.ContextTeamView.class, Views.ContextStationView.class})
    private Boolean isHeavy;
    private Integer quality;
    @JsonView({Views.ContextTeamView.class, Views.ContextStationView.class})
    private List<EquipmentDto> equipments;

    @Override
    public int compareTo(@NotNull VehicleDto o) {
        return Integer.compare(quality,o.quality);
    }
}
