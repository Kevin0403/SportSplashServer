package com.example.sportsplash.dao;

import com.example.sportsplash.sports.BadmintonMatch;
import com.example.sportsplash.sports.BadmintonMatchState;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BadmintonMatchStatusDao extends JpaRepository<BadmintonMatchState, Integer> {
    @Query("SELECT bms FROM BadmintonMatchState bms WHERE bms.match.id = :matchId ORDER BY bms.id DESC")
    List<BadmintonMatchState> findLastStatusByMatchId(@Param("matchId") int matchId);
}
