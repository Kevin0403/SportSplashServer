package com.example.sportsplash.controller;

import com.example.sportsplash.service.sportsservice;
import com.example.sportsplash.sports.Tournament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(allowedHeaders = "*")

public class TournamentController {
    @Autowired
    private sportsservice sportsservice;

    /*For creating the tournament*/
    @PostMapping("/tournaments")
    public Tournament createTournament(@RequestBody Tournament tournament) {
        return this.sportsservice.createTournament(tournament);}

    /*For getting all the tournaments created*/
    @GetMapping("/getTournaments")
    public List<Tournament> getTournament(){
        return this.sportsservice.getTournament();
    }
    /*For updating the tournament*/
    @PutMapping("/tournament")
    public Tournament updateTournament(@RequestBody Tournament tournament){
        return this.sportsservice.updateTournament(tournament);}

    /*For getting the tournament by id*/
    @GetMapping("/getTournament/{id}")
    public Tournament gettournament(@PathVariable int id){
        return this.sportsservice.gettournament(id);
    }
    @DeleteMapping("/tournament/{id}")
    public ResponseEntity<HttpStatus> deletetournament(@PathVariable int id) {
        try {
            this.sportsservice.deletetournament(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
