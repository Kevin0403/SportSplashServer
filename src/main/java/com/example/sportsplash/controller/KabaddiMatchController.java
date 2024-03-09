package com.example.sportsplash.controller;

import com.example.sportsplash.service.sportsservice;
import com.example.sportsplash.sports.BadmintonMatch;
import com.example.sportsplash.sports.KabaddiMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(allowedHeaders = "*")

public class KabaddiMatchController {

    @Autowired
    private sportsservice sportsservice;

    /*For getting the kabaddi match by id*/
    @GetMapping("/getKabaddiMatch/{id}")
    public KabaddiMatch getKabaddiMatch(@PathVariable int id){
        return this.sportsservice.getKabaddiMatch(id);
    }

    /*For getting all the matches*/
    @GetMapping("/getKMatches")
    public List<KabaddiMatch> getMatches(){
        return  this.sportsservice.getKMatches();
    }

    /*For updating the BadmintonMatch*/
    @PutMapping("/updateKabaddiMatch")
    public KabaddiMatch updateKabaddiMatch(@RequestBody KabaddiMatch kabaddiMatch){
        return this.sportsservice.updateKabaddiMatch(kabaddiMatch);}

    /*For getting all the matches in perticular tournament*/
    @GetMapping("/tournamentmatch/KABADDI/{id}")
    public ResponseEntity<List<KabaddiMatch>> getKabaddiMatchesForTournament(@PathVariable int id){
        List<KabaddiMatch> matches=this.sportsservice.getKabaddiMatchesForTournament(id);
        return ResponseEntity.ok(matches);
    }

    /*For deleting the KabaddiMatch*/
    @DeleteMapping("/kabaddimatch/{id}")
    public ResponseEntity<HttpStatus> deleteKabaddiMatch(@PathVariable int id){
        try {
            this.sportsservice.deleteKabaddiMatch(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* For creating KabaddiMatch */
    @PostMapping("/createkabaddiMatch")
    public KabaddiMatch createKabaddiMatch(@RequestBody KabaddiMatch kabaddiMatch) {
        return this.sportsservice.createKabaddiMatch(kabaddiMatch);}

}
