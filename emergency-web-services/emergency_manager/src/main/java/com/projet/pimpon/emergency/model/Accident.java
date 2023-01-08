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
@Table(name = "ACCIDENT", schema = "emergency")
public class Accident {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer accidentId;

    @NotNull
    private Integer accidentIntensity;

    private Integer teamId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AccidentStatus accidentStatus;

    @NotNull
    private String accidentCoordinates;
}
