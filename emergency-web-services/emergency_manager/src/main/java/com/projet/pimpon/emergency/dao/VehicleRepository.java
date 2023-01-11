package com.projet.pimpon.emergency.dao;

import com.projet.pimpon.emergency.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    List<Vehicle> findAllByStationId(Integer stationId);
    List<Vehicle> findAllByTeamId(Integer teamId);
    List<Vehicle> findAllByTeamIdIsNull();
}
