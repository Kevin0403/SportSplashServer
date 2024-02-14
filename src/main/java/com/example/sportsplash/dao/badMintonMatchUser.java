package com.example.sportsplash.dao;

import com.example.sportsplash.sports.BadmintonMatchUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface badMintonMatchUser extends JpaRepository<BadmintonMatchUser,Integer> {
    BadmintonMatchUser findById(int id);

}
