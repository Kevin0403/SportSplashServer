package com.example.sportsplash.dao;

import com.example.sportsplash.sports.BadmintonMatch;
import com.example.sportsplash.sports.KabaddiMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface kabaddimatchdao extends JpaRepository<KabaddiMatch,Integer> {
   @Query("SELECT t FROM KabaddiMatch t WHERE t.tournament.id = :tournamentId")
   List<KabaddiMatch> findKabaddiMatchByTournamentId(int tournamentId);

   @Query("SELECT t FROM KabaddiMatch  t WHERE t.tournament.isdefault=true AND t.tournament.user.email=:email ORDER BY t.id DESC ")
   List<KabaddiMatch> findKabaddiMatchByUserId(String email);
   KabaddiMatch findById(int id);

}
