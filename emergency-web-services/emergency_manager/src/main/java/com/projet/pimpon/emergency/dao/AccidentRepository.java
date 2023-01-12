package com.projet.pimpon.emergency.dao;

import com.projet.pimpon.emergency.model.Accident;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccidentRepository extends JpaRepository<Accident, Integer> {
    @Query(value = "select a1_0.accident_id, a1_0.accident_intensity, a1_0.team_id, a1_0.accident_status, cast(a1_0.accident_coordinates as VARCHAR) from emergency.accident a1_0 where a1_0.team_id = :teamId", nativeQuery = true)
    Accident findByTeamId(@Param("teamId") Integer teamId);

    @Query(value = "select a1_0.accident_id, a1_0.accident_intensity, a1_0.team_id, a1_0.accident_status, cast(a1_0.accident_coordinates as VARCHAR) from emergency.accident a1_0", nativeQuery = true)
    List<Accident> findAll();

    @Transactional
    @Query(value = "insert into emergency.accident (accident_intensity, team_id, accident_status, accident_coordinates) values(:intensity,:teamId,:accidentStatus, cast(:coordinates as POINT))", nativeQuery = true)
    void saveAccident(@Param("intensity") Integer intensity, @Param("teamId") Integer teamId, @Param("accidentStatus") String accidentStatus, @Param("coordinates") String coordinates);

    @Query(value = "update emergency.accident a1_0 set a1_0.accident_intensity = :intensity, a1_0.team_id = :teamId, a1_0.accident_status = :accidentStatus, a1_0.accident_coordinates = cast(:coordinates as POINT) where a1_0.accident_id = :id", nativeQuery = true)
    void updateAccident(@Param("id") Integer id, @Param("intensity") Integer intensity, @Param("teamId") Integer teamId, @Param("accidentStatus") String accidentStatus, @Param("coordinates") String coordinates);
}

