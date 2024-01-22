package com.example.sportsplash.dao;

import com.example.sportsplash.sports.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface playerdao extends JpaRepository<Player,Integer> {
    @Query("SELECT p FROM Player p WHERE p.team.id = :teamId")
    List<Player> findPlayersByTeamId(@Param("teamId") int teamId);


}
