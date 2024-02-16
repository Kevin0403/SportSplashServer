package com.example.sportsplash.dao;

import com.example.sportsplash.sports.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface sportsdao extends JpaRepository<User,String> {
    @Query("SELECT s from User s where s.email = ?1")
    public User findByEmail( @Param("email") String email);


}
