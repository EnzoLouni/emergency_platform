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
@Table(name = "AGENT", schema = "emergency")
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer agentId;

    @NotNull
    private String agentName;

    private Integer teamId;

    @NotNull
    private Integer agentExhaustion;

    @NotNull
    private Integer agentQuality;

    private Integer stationId;
}
