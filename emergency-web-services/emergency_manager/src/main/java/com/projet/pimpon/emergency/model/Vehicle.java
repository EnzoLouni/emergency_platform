package com.projet.pimpon.emergency.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "VEHICLE", schema = "emergency")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_vehicle;

    @NotNull
    private Integer vehicleCapacity;

    @NotNull
    private Integer vehicleTankCapacity;

    @NotNull
    private Boolean isHeavyWeight;

    @NotNull
    private Coordinates coordinates;

    @NotNull
    private Integer quality;
}
