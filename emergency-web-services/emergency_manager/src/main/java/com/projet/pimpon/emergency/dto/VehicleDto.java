package com.projet.pimpon.emergency.dto;

import com.projet.pimpon.emergency.model.Coordinates;
import com.projet.pimpon.emergency.model.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDto {
    private Team team;
    private Integer vehicleCapacity;
    private Integer vehicleTankCapacity;
    private Boolean isHeavyWeight;
    private Coordinates coordinates;
    private Integer quality;
}
