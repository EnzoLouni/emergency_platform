package com.projet.pimpon.emergency.dao;

import com.projet.pimpon.emergency.model.Accident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccidentRepository extends JpaRepository<Accident, Integer> {
    @Query(value = "select a1_0.accident_id, a1_0.accident_intensity, a1_0.team_id, a1_0.accident_status, cast(a1_0.accident_coordinates as VARCHAR) from emergency.accident a1_0 where a1_0.team_id = :teamId", nativeQuery = true)
    Accident findByTeamId(@Param("teamId") Integer teamId);
}
