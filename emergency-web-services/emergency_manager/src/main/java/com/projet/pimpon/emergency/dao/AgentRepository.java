package com.projet.pimpon.emergency.dao;

import com.projet.pimpon.emergency.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Integer> {
    List<Agent> findAllByStationId(Integer stationId);
    List<Agent> findAllByStationIdAndTeamIdIsNull(Integer stationId);
    List<Agent> findAllByTeamId(Integer teamId);
    List<Agent> findAllByTeamIdIsNull();
}
