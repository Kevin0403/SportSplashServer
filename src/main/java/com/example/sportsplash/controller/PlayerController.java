package com.example.sportsplash.controller;

import com.example.sportsplash.service.sportsservice;
import com.example.sportsplash.sports.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class PlayerController {
    @Autowired
    private sportsservice sportsservice;

    /*For getting the list of Players*/
    @GetMapping("/getPlayers")
    public List<Player> getPlayers(){
        return this.sportsservice.getPlayes();
    }
    /*For updating the player*/
    @PutMapping("/player")
    public Player updatePlayer(@RequestBody Player player){
        return this.sportsservice.updatePlayer(player);
    }

    /*For getting all players in perticular team*/
    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<Player>> getPlayersForTeam(@PathVariable int teamId) {
        List<Player> players = this.sportsservice.getPlayersForTeam(teamId);
        return ResponseEntity.ok(players);}
    /*For deleting the player*/
    @DeleteMapping("/player/{id}")
    public ResponseEntity<HttpStatus> deleteplayer(@PathVariable int id){
        try {
            this.sportsservice.deleteplayer(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /*For creating the player*/
    @PostMapping("/player")
    public Player createPlayer(@RequestBody Player player){
        return this.sportsservice.createPlayer(player);
    }



}
