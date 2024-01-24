package com.example.sportsplash.dao;

import com.example.sportsplash.sports.BadmintonMatch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface badMintonMatchdao extends JpaRepository<BadmintonMatch,Integer> {
}
