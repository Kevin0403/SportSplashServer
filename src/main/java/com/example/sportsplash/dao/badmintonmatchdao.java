package com.example.sportsplash.dao;

import com.example.sportsplash.sports.BadmintonMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface badmintonmatchdao extends JpaRepository<BadmintonMatch,Integer> {
    @Query("SELECT t FROM BadmintonMatch t WHERE t.tournament.id = :tournamentId")
    List<BadmintonMatch> findBadmintonMatchByTournamentId( int tournamentId);
    BadmintonMatch findById(int id);
}
