package com.example.sportsplash.dao;

import com.example.sportsplash.sports.Tournaments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface tournamentdao extends JpaRepository<Tournaments,String> {

}
