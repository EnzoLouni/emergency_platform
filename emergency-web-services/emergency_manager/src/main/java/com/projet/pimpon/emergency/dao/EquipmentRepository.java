package com.projet.pimpon.emergency.dao;

import com.projet.pimpon.emergency.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
    List<Equipment> findAllByVehicleId(Integer vehicleId);
}
