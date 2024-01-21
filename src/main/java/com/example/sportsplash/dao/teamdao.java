package com.example.sportsplash.dao;

import com.example.sportsplash.sports.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface teamdao extends JpaRepository<Team, Integer> {


    Team findById(int id);
}
