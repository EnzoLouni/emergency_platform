package com.projet.pimpon.emergency.dao;

import com.projet.pimpon.emergency.model.Agent;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Integer> {
    List<Agent> findAllByStationId(Integer stationId);
    List<Agent> findAllByStationIdAndTeamIdIsNull(Integer stationId);
    List<Agent> findAllByTeamId(Integer teamId);
    @Transactional
    @Modifying
    @Query(value = "update emergency.agent set team_id = :teamId where agent_id in (:ids)", nativeQuery = true)
    void updateTeamId(@Param("teamId") Integer teamId, @Param("ids") List<Integer> ids);
}
