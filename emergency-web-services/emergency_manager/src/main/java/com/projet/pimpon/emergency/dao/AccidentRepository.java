package com.projet.pimpon.emergency.dao;

import com.projet.pimpon.emergency.model.Accident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccidentRepository extends JpaRepository<Accident, Integer> {
}
