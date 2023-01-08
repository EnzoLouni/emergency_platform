package com.projet.pimpon.emergency.dao;

import com.projet.pimpon.emergency.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    @Query(value = "select s1_0.sensor_id,CAST(s1_0.sensor_coordinates as VARCHAR),s1_0.sensor_status from emergency.sensor s1_0 where s1_0.sensor_coordinates ~= CAST(:pGpoint as POINT)", nativeQuery = true)
    Sensor findBySensorCoordinates(@Param("pGpoint") String pGpoint);
}
