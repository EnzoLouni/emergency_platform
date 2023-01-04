package com.projet.pimpon.emergency.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TEAM", schema = "emergency")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_team;

    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name="ACCIDENT",
        joinColumns = {@JoinColumn(name = "id_accident", referencedColumnName = "id_team")}
    )
    private List<Accident> accidents;

    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name="AGENT",
            joinColumns = {@JoinColumn(name = "id_agent", referencedColumnName = "id_team")}
    )
    private List<Agent> agents;

    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name="VEHICLES",
            joinColumns = {@JoinColumn(name = "id_vehicles", referencedColumnName = "id_team")}
    )
    private List<Vehicle> vehicles;

    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name="STATION",
            joinColumns = {@JoinColumn(name = "id_station", referencedColumnName = "id_team")}
    )
    private List<Station> stations;

    @NotNull
    private Integer quality;
}
