package com.example.sportsplash.controller;

import com.example.sportsplash.service.sportsservice;
import com.example.sportsplash.sports.BadmintonMatch;
import com.example.sportsplash.sports.KabaddiMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class KabaddiMatchController {

    @Autowired
    private sportsservice sportsservice;

    /*For getting the badminton match by id*/
    @GetMapping("/getKabaddiMatch/{id}")
    public KabaddiMatch getKabaddiMatch(@PathVariable int id){
        return this.sportsservice.getKabaddiMatch(id);
    }
}
