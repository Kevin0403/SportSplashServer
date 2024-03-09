package com.example.sportsplash.controller;

import com.example.sportsplash.service.sportsservice;
import com.example.sportsplash.sports.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(allowedHeaders = "*")

public class TeamController {

    @Autowired
    private sportsservice sportsservice;

    /*For getting all the teams created*/
    @GetMapping("/getTeams")
    public List<Team> getTeam() {
        return this.sportsservice.getTeam();
    }

    /*For updating the team*/
    @PutMapping("/team")
    public Team updateTeam(@RequestBody Team team) {
        return this.sportsservice.updateTeam(team);
    }

    /*For getting all the teams in perticular tournament*/
    @GetMapping("/tournament/{tournamentId}")
    public ResponseEntity<List<Team>> getTeamsForTournament(@PathVariable int tournamentId) {
        List<Team> teams = this.sportsservice.getTeamsForTournament(tournamentId);
        return ResponseEntity.ok(teams);
    }

    /*For getting the team by id*/
    @GetMapping("/getTeam/{id}")
    public Team getteam(@PathVariable int id){
        return this.sportsservice.getteam(id);
    }

    /*For deleting the team*/
    @DeleteMapping("/team/{id}")
    public ResponseEntity<HttpStatus> deleteteam(@PathVariable int id){
        try {
            this.sportsservice.deleteteam(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /*For creating the team*/
    @PostMapping("/team")
    public Team createTeam(@RequestBody Team team){
        return this.sportsservice.createTeam(team);
    }
    /*For deleting the team with players*/
    @DeleteMapping("/deleteteamplayer/{id}")
    public void deleteTeamPlayers(@PathVariable int id){
        this.sportsservice.deleteTeamPlayers(id);}

}


