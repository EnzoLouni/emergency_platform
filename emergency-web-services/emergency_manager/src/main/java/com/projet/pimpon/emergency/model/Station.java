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
@Table(name = "STATION", schema = "emergency")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_station;

    @NotNull
    private String fullName;

    @NotNull
    private Coordinates coordinates;
}
