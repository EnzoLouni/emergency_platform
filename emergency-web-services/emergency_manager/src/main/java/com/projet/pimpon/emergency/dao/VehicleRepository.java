package com.projet.pimpon.emergency.dao;

import com.projet.pimpon.emergency.model.Vehicle;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Integer> {
    List<Vehicle> findAllByStationId(Integer stationId);
    List<Vehicle> findAllByStationIdAndTeamIdIsNull(Integer stationId);
    List<Vehicle> findAllByTeamId(Integer teamId);
    List<Vehicle> findAllByTeamIdIsNull();
    @Transactional
    @Modifying
    @Query(value = "update emergency.vehicle set team_id = :teamId where vehicle_id in (:ids)", nativeQuery = true)
    void updateTeamId(@Param("teamId") Integer teamId, @Param("ids") List<Integer> ids);
}
