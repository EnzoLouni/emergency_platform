package com.projet.pimpon.emergency.dao;

import com.projet.pimpon.emergency.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station, Integer> {
    @Query(value = "select cast(s.station_coordinates as VARCHAR) from emergency.station s where s.station_id = :stationId", nativeQuery = true)
    String findCoordinatesByStationId(@Param("stationId") Integer stationId);
}
