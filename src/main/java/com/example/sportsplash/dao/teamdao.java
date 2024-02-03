package com.example.sportsplash.dao;

import com.example.sportsplash.sports.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface teamdao extends JpaRepository<Team, Integer> {


    Team findById(int id);
    @Query("SELECT t FROM Team t WHERE t.tournament.id = :tournamentId")
    List<Team> findTeamsByTournamentId(@Param("tournamentId") int tournamentId);

}
