package com.example.sportsplash.dao;

import com.example.sportsplash.sports.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface playerdao extends JpaRepository<Player,Integer> {
}
