package com.example.sportsplash.service;

import com.example.sportsplash.dao.*;
import com.example.sportsplash.sports.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class sportsserviceimpl implements sportsservice{

      @Autowired
      private sportsdao sd;
      @Autowired
      private tournamentdao td;
      @Autowired
      private teamdao teamdao1;
      @Autowired
      private playerdao pd;
      @Autowired
      private badMintonMatchdao badMintonMatchdao;

    public sportsserviceimpl() {
        super();
    }

    @Override
    public User verifyUser(User s) {
        String email = s.getEmail();
        User s1 = sd.findByEmail(email);

        if (s1.getPassword().equals(s.getPassword())) {
            return s1;

        } else
            return null;
    }

    @Override
    public Tournament gettournament(int id) {
        Tournament t1=td.findById(id);
            return t1;



    }
    @Override
    public List<Team> getTeamsForTournament(int tournamentId) {
        return teamdao1.findTeamsByTournamentId(tournamentId);
    }


    @Override
    public Team getteam(int id) {
        Team team=teamdao1.findById(id);
        return team;
    }

    @Override
    public List<Player> getPlayes() {
        return pd.findAll();
    }

    @Override
    public List<User> getUser(){


        return  sd.findAll();
    }
    @Override
    public List<Player> getPlayersForTeam(int teamId) {
        return pd.findPlayersByTeamId(teamId);
    }


    @Override
    public List<Tournament> getTournament() {
        return td.findAll();
    }

    @Override
    public List<Team> getTeam() {
        return teamdao1.findAll();
    }

    @Override
    public User createUser(User s) {
       sd.save(s);
       return s;
    }

    @Override
    public User updateUser(User s) {
        sd.save(s);
        return  s;
    }

    @Override
    public void deleteUser(String email) {
        User entity=sd.getOne(email);
        sd.delete(entity);

    }
    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullpath=path+File.separator+fileName;
        InputStream is=new FileInputStream(fullpath);

        return is;
    }

    @Override
    public String uploadImage(String path, MultipartFile file) throws  IOException {

        String name = file.getOriginalFilename();
        String randomId = UUID.randomUUID().toString();
        String fileName1 = randomId.concat(name.substring(name.lastIndexOf(".")));
        String filepath = path + File.separator + fileName1;

        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }
        Files.copy(file.getInputStream(), Paths.get(filepath));
        return fileName1;
    }

//    @Override
//    public Tournaments createTournament(Tournaments t) {
//        td.save(t);
//        return t;
//
//    }
    @Override
    public User retrieveOrCreateUser(User user) {
        User existingUser = sd.findByEmail(user.getEmail());

        if (existingUser == null) {
            sd.save(user);
            return user;
        } else {
            return existingUser;
        }
    }

    @Override
    @Transactional
    public Tournament createTournament(Tournament t) {
        User existingUser = sd.findByEmail(t.getUser().getEmail());

        if (existingUser == null) {
            User newUser = new User();
            newUser.setEmail(t.getUser().getEmail());
            sd.save(newUser);
            t.setUser(newUser);
        } else {
            t.setUser(existingUser);
        }

        if (t.getUser() == null) {
            throw new IllegalArgumentException("User cannot be null in the tournament.");
        }

        td.save(t);
        return t;
    }

    @Override
    public Team createTeam(Team t) {
        Tournament tournaments=td.findById(t.getTournament().getId());
        if(tournaments==null){
            Tournament tournaments1=new Tournament();
            tournaments1.setId(t.getTournament().getId());
            td.save(tournaments1);
            t.setTournament(tournaments1);
        }
        else
        {
            t.setTournament(tournaments);
        }
        if(t.getTournament()==null) {
            throw new IllegalArgumentException("null");
        }
            teamdao1.save(t);

            return t;
    }
    @Override
    public Player createPlayer(Player p){
         Team team=teamdao1.findById(p.getTeam().getId());
         if(team==null){
             Team team1=new Team();
             team1.setId(p.getTeam().getId());
             teamdao1.save(team1);
             p.setTeam(team1);

         }
         else {
             p.setTeam(team);
         }
         if (p.getTeam()==null){
             throw new IllegalArgumentException("null");
         }
         pd.save(p);
         return p;
    }
    @Override
    public BadmintonMatch createBadmintonMatch(BadmintonMatch badmintonMatch) {
        List<Team> teamList = badmintonMatch.getTeamList();
        Tournament tournament = badmintonMatch.getTournament();
        User user = tournament.getUser();

        User existingUser = sd.findByEmail(user.getEmail());
        if (existingUser == null) {
            user.setEmail("Piyu1006@gmail.com");
            sd.save(user);  // Save User if it doesn't exist
        } else {
            user = existingUser; // Update reference to existing User
        }

        // Handle Tournament Persistence
        Tournament existingTournament = td.findById(tournament.getId());
        if (existingTournament == null) {
            Tournament newTournament = new Tournament();
            newTournament.setId(tournament.getId());
            newTournament.setUser(user);
            td.save(newTournament);
            badmintonMatch.setTournament(newTournament);
        } else {
            badmintonMatch.setTournament(existingTournament); // Use existing Tournament
        }

        // ... (Steps 5-8 remain the same)
        // Ensure bidirectional relationship for Team -> Tournament
        for (Team team : teamList) {
            // Check if Team exists in the database
            Team existingTeam = teamdao1.findById(team.getId());
            if (existingTeam == null) {
                // If Team does not exist, save it to the database
                teamdao1.save(team);
            } else {
                // If Team exists, update the reference
                team = existingTeam;
            }

            team.setTournament(badmintonMatch.getTournament());
        }

        // Ensure bidirectional relationship for Tournament -> User
        badmintonMatch.getTournament().setUser(user);

        // Save the match to the database
      badMintonMatchdao.save(badmintonMatch);

        return badmintonMatch;
    }



}
