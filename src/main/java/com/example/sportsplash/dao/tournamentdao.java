package com.example.sportsplash.dao;

import com.example.sportsplash.sports.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface tournamentdao extends JpaRepository<Tournament,String> {

    Tournament findById(int id);

    Tournament getReferenceById(int id);
}
