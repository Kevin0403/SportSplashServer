package com.example.sportsplash;

import com.example.response.FileResponse;
import com.example.sportsplash.service.sportsservice;
import com.example.sportsplash.sports.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@CrossOrigin("*")
public class mycontroller {
    @Autowired
    private sportsservice sportsservice;
    @Value("${project.image}")
    private  String path;
    /*For getting all the users */
    @GetMapping("/signup")
    public List<User> getUser() {
        return this.sportsservice.getUser();
    }
    /*For getting all the tournaments created*/
    @GetMapping("/getTournaments")
    public List<Tournament> getTournament(){
        return this.sportsservice.getTournament();
    }
    /*For getting all the teams created*/
    @GetMapping("/getTeams")
    public List<Team> getTeam(){
        return this.sportsservice.getTeam();
    }
    /*For getting all the matches*/
    @GetMapping("/getMatches")
    public List<BadmintonMatch> getMatches(){return  this.sportsservice.getMatches();}

    /*For getting the list of Players*/
    @GetMapping("/getPlayers")
    public List<Player> getPlayers(){
        return this.sportsservice.getPlayes();
    }
    /* For creating new user*/
    @PostMapping("/signup")
    public User createUser(@RequestBody User s){
        return this.sportsservice.createUser(s);
    }
    /*For updating the user*/
    @PutMapping("/signup")
    public User updateUser(@RequestBody User s){
        return this.sportsservice.updateUser(s);
    }
    /*For updating the tournament*/
    @PutMapping("/tournament")
    public Tournament updateTournament(@RequestBody Tournament tournament){
        return this.sportsservice.updateTournament(tournament);}
    /*For updating the team*/
    @PutMapping("/team")
    public Team updateTeam(@RequestBody Team team){
        return this.sportsservice.updateTeam(team);
    }
    /*For updating the player*/
    @PutMapping("/player")
    public Player updatePlayer(@RequestBody Player player){
        return this.sportsservice.updatePlayer(player);
    }
    /*For updating the BadmintonMatch*/
    @PutMapping("/badmintonMatch")
    public BadmintonMatch updateBadmintonMatch(@RequestBody BadmintonMatch badmintonMatch){
        return this.sportsservice.updateBadmintonMatch(badmintonMatch);}
    /*For verifying the user*/
    @PostMapping("/verifyUser")
    public User verifyUser(@RequestBody User s){
        return this.sportsservice.verifyUser(s);
    }
    /*For getting the tournament by id*/
    @GetMapping("/getTournament/{id}")
    public Tournament gettournament(@PathVariable int id){
        return this.sportsservice.gettournament(id);
    }
    /*For getting all the teams in perticular tournament*/
    @GetMapping("/tournament/{tournamentId}")
    public ResponseEntity<List<Team>> getTeamsForTournament(@PathVariable int tournamentId) {
        List<Team> teams = this.sportsservice.getTeamsForTournament(tournamentId);
        return ResponseEntity.ok(teams);}
    /*For getting all the matches in perticular tournament*/
    @GetMapping("/tournamentmatch/{id}")
    public ResponseEntity<List<BadmintonMatch>> getMatchesForTournament(@PathVariable int id){
        List<BadmintonMatch> matches=this.sportsservice.getMatchesForTournament(id);
        return ResponseEntity.ok(matches);}
    /* For creating BadmintonMatch */
    @PostMapping("/create")
    public BadmintonMatch createBadmintonMatch(@RequestBody BadmintonMatch badmintonMatch) {
        return this.sportsservice.createBadmintonMatch(badmintonMatch);}

    /*For getting all players in perticular team*/
    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<Player>> getPlayersForTeam(@PathVariable int teamId) {
        List<Player> players = this.sportsservice.getPlayersForTeam(teamId);
        return ResponseEntity.ok(players);}
    /*For getting the team by id*/
    @GetMapping("/getTeam/{id}")
    public Team getteam(@PathVariable int id){
        return this.sportsservice.getteam(id);
    }
    /*For getting the badminton match by id*/
    @GetMapping("/getBadmintonMatch/{id}")
    public  BadmintonMatch getBadmintonMatch(@PathVariable int id){
        return this.sportsservice.getBadmintonMatch(id);
    }
    /*For deleting the user*/
    @DeleteMapping("/signup/{email}")
    public ResponseEntity<HttpStatus> deletesports(@PathVariable String email) {
        try {
            this.sportsservice.deleteUser(email);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /*For deleting the tournament*/
    @DeleteMapping("/tournament/{id}")
    public ResponseEntity<HttpStatus> deletetournament(@PathVariable int id) {
        try {
            this.sportsservice.deletetournament(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
    /*For deleting the team with players*/
    @DeleteMapping("/deleteteamplayer/{id}")
    public void deleteTeamPlayers(@PathVariable int id){
        this.sportsservice.deleteTeamPlayers(id);}

    /*For creating the tournament*/
    @PostMapping("/tournaments")
    public Tournament createTournament(@RequestBody Tournament tournament) {
        return this.sportsservice.createTournament(tournament);}
    /*For creating the team*/
    @PostMapping("/team")
    public Team createTeam(@RequestBody Team team){
        return this.sportsservice.createTeam(team);
    }
    /*For creating the player*/
    @PostMapping("/player")
    public Player createPlayer(@RequestBody Player player){
        return this.sportsservice.createPlayer(player);
    }
    /*For uploading the file*/
    @PostMapping("/upload")
    public ResponseEntity<FileResponse> fileUpload(@RequestParam("image")MultipartFile image) throws IOException {
        String fileName= null;
        try {
            fileName = this.sportsservice.uploadImage(path,image);
        } catch (IOException e) {
            return new ResponseEntity<>(new FileResponse(fileName,"Image is not successfully uploaded"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new FileResponse(fileName,"Image is successfully uploaded"),HttpStatus.OK);
    }
    /*For serving the image*/
    @GetMapping(value = "/images/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE )
    public void downloadImage(@PathVariable("imageName")String imageName, HttpServletResponse response) throws IOException {
        InputStream resource=this.sportsservice.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }
}
