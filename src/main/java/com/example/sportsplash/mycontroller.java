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
        return ResponseEntity.ok(teams);
    }
    /* For creating BadmintonMatch */
    @PostMapping("/create")
    public BadmintonMatch createBadmintonMatch(@RequestBody BadmintonMatch badmintonMatch) {

            return this.sportsservice.createBadmintonMatch(badmintonMatch);
    }
    /*For getting all players in perticular team*/
    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<Player>> getPlayersForTeam(@PathVariable int teamId) {
        List<Player> players = this.sportsservice.getPlayersForTeam(teamId);
        return ResponseEntity.ok(players);
    }
    @GetMapping("/getTeam/{id}")
    public Team getteam(@PathVariable int id){
        return this.sportsservice.getteam(id);
    }

    @DeleteMapping("/signup/{email}")
    public ResponseEntity<HttpStatus> deletesports(@PathVariable String email) {
        try {
            this.sportsservice.deleteUser(email);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Value("${project.image}")
    private  String path;

    @PostMapping("/tournaments")
    public Tournament createTournament(@RequestBody Tournament tournament) {

        return this.sportsservice.createTournament(tournament);
    }
    @PostMapping("/team")
    public Team createTeam(@RequestBody Team team){
        return this.sportsservice.createTeam(team);
    }
    @PostMapping("/player")
    public Player createPlayer(@RequestBody Player player){
        return this.sportsservice.createPlayer(player);
    }

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
    @GetMapping(value = "/images/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE )
    public void downloadImage(@PathVariable("imageName")String imageName, HttpServletResponse response) throws IOException {
        InputStream resource=this.sportsservice.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }
}
