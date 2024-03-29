package com.example.sportsplash.service;

import com.example.sportsplash.sports.*;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity createTournament(Tournament t);
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

    void deleteplayer(int id);

    void deleteBadmintonMatch(int id);

    void deleteTeamPlayers(int id);

    List<BadmintonMatch> getMatchesForTournament(int id);

    Tournament updateTournament(Tournament tournament);

    Team updateTeam(Team team);

    Player updatePlayer(Player player);

    BadmintonMatch updateBadmintonMatch(BadmintonMatch badmintonMatch);

    BadmintonMatch getBadmintonMatch(int id);



    List<BadmintonMatch> getMatches();

    KabaddiMatch getKabaddiMatch(int id);

   

    KabaddiMatch updateKabaddiMatch(KabaddiMatch kabaddiMatch);

    List<KabaddiMatch> getKabaddiMatchesForTournament(int id);

    void deleteKabaddiMatch(int id);

    KabaddiMatch createKabaddiMatch(KabaddiMatch kabaddiMatch);

    List<KabaddiMatch> getKMatches();

    List<Object> getAlltheMatches();


    List<Tournament> getAlltheTournamentsByUserEmail(String email);

    List<Object> getAlltheMAtchesByUserEmail(String email);
}
