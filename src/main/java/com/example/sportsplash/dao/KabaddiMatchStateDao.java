package com.example.sportsplash.dao;

import com.example.sportsplash.sports.BadmintonMatchState;
import com.example.sportsplash.sports.KabaddiMatchState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KabaddiMatchStateDao extends JpaRepository<KabaddiMatchState, Integer> {

    @Query("SELECT bms FROM KabaddiMatchState bms WHERE bms.match.id = :matchId ORDER BY bms.id DESC")
    List<KabaddiMatchState> findLastStatusByMatchId(@Param("matchId") int matchId);


}
