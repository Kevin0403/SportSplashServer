package com.example.sportsplash.controller;

import com.example.sportsplash.service.sportsservice;
import com.example.sportsplash.sports.BadmintonMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class BadmintonMatchController {
    @Autowired
    private sportsservice sportsservice;

    /*For getting all the matches*/
    @GetMapping("/getMatches")
    public List<BadmintonMatch> getMatches(){return  this.sportsservice.getMatches();}

    /*For updating the BadmintonMatch*/
    @PutMapping("/badmintonMatch")
    public BadmintonMatch updateBadmintonMatch(@RequestBody BadmintonMatch badmintonMatch){
        return this.sportsservice.updateBadmintonMatch(badmintonMatch);}
    /*For getting all the matches in perticular tournament*/
    @GetMapping("/tournamentmatch/{id}")
    public ResponseEntity<List<BadmintonMatch>> getMatchesForTournament(@PathVariable int id){
        List<BadmintonMatch> matches=this.sportsservice.getMatchesForTournament(id);
        return ResponseEntity.ok(matches);}
    /*For getting the badminton match by id*/
    @GetMapping("/getBadmintonMatch/{id}")
    public  BadmintonMatch getBadmintonMatch(@PathVariable int id){
        return this.sportsservice.getBadmintonMatch(id);
    }
    /*For deleting the badmintonMatch*/
    @DeleteMapping("/match/{id}")
    public ResponseEntity<HttpStatus> deleteBadmintonMatch(@PathVariable int id){
        try {
            this.sportsservice.deleteBadmintonMatch(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* For creating BadmintonMatch */
    @PostMapping("/create")
    public BadmintonMatch createBadmintonMatch(@RequestBody BadmintonMatch badmintonMatch) {
        return this.sportsservice.createBadmintonMatch(badmintonMatch);}
}
