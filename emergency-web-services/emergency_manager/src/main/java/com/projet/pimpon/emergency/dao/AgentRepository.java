package com.projet.pimpon.emergency.dao;

import com.projet.pimpon.emergency.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Integer> {

}
