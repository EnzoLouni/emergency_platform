package com.projet.pimpon.emergency.dao;

import com.projet.pimpon.emergency.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
    @Query(value = "insert into emergency.team (team_quality) values(:quality)", nativeQuery = true)
    Team createTeam(@Param("quality") Integer quality);
}
