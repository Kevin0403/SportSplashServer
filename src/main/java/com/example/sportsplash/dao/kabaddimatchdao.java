package com.example.sportsplash.dao;

import com.example.sportsplash.sports.KabaddiMatch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface kabaddimatchdao extends JpaRepository<KabaddiMatch,Integer> {
}
