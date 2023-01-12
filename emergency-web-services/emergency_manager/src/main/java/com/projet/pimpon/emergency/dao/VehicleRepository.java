package com.projet.pimpon.emergency.dao;

import com.projet.pimpon.emergency.model.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Integer> {
    List<Vehicle> findAllByStationId(Integer stationId);
    List<Vehicle> findAllByStationIdAndTeamIdIsNull(Integer stationId);
    List<Vehicle> findAllByTeamId(Integer teamId);
    List<Vehicle> findAllByTeamIdIsNull();
}
