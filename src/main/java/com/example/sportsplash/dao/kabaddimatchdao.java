package com.example.sportsplash.dao;

import com.example.sportsplash.sports.BadmintonMatch;
import com.example.sportsplash.sports.KabaddiMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface kabaddimatchdao extends JpaRepository<KabaddiMatch,Integer> {
   @Query("SELECT t FROM KabaddiMatch t WHERE t.tournament.id = :tournamentId")
   List<BadmintonMatch> findKabaddiMatchByTournamentId(int tournamentId);
   KabaddiMatch findById(int id);
}
