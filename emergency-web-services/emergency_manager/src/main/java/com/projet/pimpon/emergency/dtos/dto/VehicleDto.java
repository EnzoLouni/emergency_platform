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
import org.postgresql.geometric.PGpoint;

import java.util.List;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDto implements Comparable{
    @JsonView({Views.ContextTeamView.class, Views.ContextStationView.class})
    private Integer id;
    @JsonView({Views.ContextTeamView.class, Views.ContextStationView.class})
    private Integer capacity;
    @JsonView({Views.ContextTeamView.class, Views.ContextStationView.class})
    private Integer tankCapacity;
    @JsonView({Views.ContextTeamView.class, Views.ContextStationView.class})
    private Boolean isHeavy;
    @JsonView({Views.ContextTeamView.class})
    private PGpoint coordinates;
    private Integer quality;
    @JsonView({Views.ContextTeamView.class})
    private List<EquipmentDto> equipments;

    @Override
    public int compareTo(@NotNull Object o) {
        return 0;
    }
}
