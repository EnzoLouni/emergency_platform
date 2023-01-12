package com.projet.pimpon.emergency.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "EQUIPMENT", schema = "emergency")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer equipmentId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EquipmentType equipmentType;

    private Integer vehicleId;

    private String equipmentFeatures;

    @NotNull
    private Integer equipmentQuality;
}
