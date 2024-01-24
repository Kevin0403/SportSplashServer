package com.example.sportsplash.service;

import com.example.sportsplash.sports.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface sportsservice {
    public List<User> getUser();
    public User verifyUser(User s);
    public User createUser(User s);
    public User updateUser(User s);
    public void deleteUser(String email);
    public Tournament createTournament(Tournament t);
    public Team createTeam(Team t);
    InputStream getResource(String path, String fileName) throws FileNotFoundException;

    public String uploadImage(String path, MultipartFile file) throws IOException;
    public User retrieveOrCreateUser(User user);

    Player createPlayer(Player p);

    List<Player> getPlayersForTeam(int teamId);

    List<Tournament> getTournament();

    List<Team> getTeam();

    Tournament gettournament(int id);

    List<Team> getTeamsForTournament(int tournamentId);

    Team getteam(int id);

    List<Player> getPlayes();

    BadmintonMatch createBadmintonMatch(BadmintonMatch badmintonMatch);

    void deletetournament(int id);

    void deleteteam(int id);
}
