package com.example.sportsplash.dao;

import com.example.sportsplash.sports.Tournament;
import com.example.sportsplash.sports.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface tournamentdao extends JpaRepository<Tournament,String> {

    Tournament findById(int id);

    Tournament getReferenceById(int id);
    @Query("SELECT t FROM Tournament t WHERE t.user.email = :email AND t.isdefault = true")
    List<Tournament> findAllTournamentsByUserEmail(String email);
}
