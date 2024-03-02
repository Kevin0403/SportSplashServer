package com.example.sportsplash.service;

import com.example.sportsplash.dao.*;
import com.example.sportsplash.sports.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
      private badmintonmatchdao badMintonMatchdao;
      @Autowired
      private  kabaddimatchdao kabaddimatchdao;



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
    public BadmintonMatch getBadmintonMatch(int id) {
        BadmintonMatch badmintonMatch=badMintonMatchdao.findById(id);
        return badmintonMatch;
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

        for (Game game : Game.values()) {
            Tournament tournament=new Tournament();
            tournament.setTournamentName(null);
              tournament.setGame(game);
              tournament.setUser(s);
              tournament.setIsdefault(true);
              td.save(tournament);
        }
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
        Tournament tournament=td.findById(t.getTournament().getId());
        if(tournament==null){
            if (t.getTournament().isIsdefault()) {
                String email = t.getTournament().getUser().getEmail();

                Tournament defaulttournament = td.findAllTournamentsByUserEmail(email, t.getTournament().getGame());

                if (defaulttournament != null) {
                    t.setTournament(defaulttournament);
                } else {
                    throw new IllegalStateException("No default tournament found for the user");
                }

            } else {

                tournament = td.findById(tournament.getId());
                t.setTournament(tournament);

                if (tournament == null) {
                    throw new IllegalArgumentException("Tournament does not exist in the database");
                }

            }
        }
        else
        {
            t.setTournament(tournament);
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
    public List<BadmintonMatch> getMatches() {
       return badMintonMatchdao.findAll();
    }

    @Override
    public KabaddiMatch getKabaddiMatch(int id) {
        KabaddiMatch kabaddiMatch= kabaddimatchdao.findById(id);
        return kabaddiMatch;

    }


    @Override
    public KabaddiMatch updateKabaddiMatch(KabaddiMatch kabaddiMatch) {
        if (kabaddiMatch == null) {
            throw new IllegalArgumentException("BadmintonMatch object cannot be null");
        }
        Team team1 = kabaddiMatch.getTeam1();
        if (team1 == null) {
            throw new IllegalArgumentException("Team1 cannot be null");

        }
        team1=teamdao1.findById(team1.getId());
        kabaddiMatch.setTeam1(team1);
        if (team1 == null) {
            throw new IllegalArgumentException("Team 1 does not exist in the database");

        }
        Team team2 = kabaddiMatch.getTeam2();
        if (team2 == null) {
            throw new IllegalArgumentException("Team2 cannot be null");

        }
        team2=teamdao1.findById(team2.getId());
        kabaddiMatch.setTeam2(team2);
        if (team2 == null) {
            throw new IllegalArgumentException("Team 2 does not exist in the database");
        }


        Tournament tournament =kabaddiMatch.getTournament();
        if (tournament == null) {
            throw new IllegalArgumentException("Tournament cannot be null");
        }
        tournament=td.findById(tournament.getId());
        kabaddiMatch.setTournament(tournament);

        if (tournament == null) {
            throw new IllegalArgumentException("Tournament does not exist in the database");
        }
      kabaddimatchdao.save(kabaddiMatch);
        return kabaddiMatch;

    }

    @Override
    public List<BadmintonMatch> getKabaddiMatchesForTournament(int id) {
        return kabaddimatchdao.findKabaddiMatchByTournamentId(id);
    }

    @Override
    public void deleteKabaddiMatch(int id) {

        KabaddiMatch kabaddiMatch=kabaddimatchdao.getReferenceById(id);
        kabaddiMatch.setTeam1(null);
        kabaddiMatch.setTeam2(null);
        kabaddiMatch.setTournament(null);
        kabaddimatchdao.delete(kabaddiMatch);
    }

    @Override
    public KabaddiMatch createKabaddiMatch(KabaddiMatch kabaddiMatch) {
        if (kabaddiMatch == null) {
            throw new IllegalArgumentException("BadmintonMatch object cannot be null");
        }
        Team team1 = kabaddiMatch.getTeam1();
        if (team1 == null) {
            throw new IllegalArgumentException("Team1 cannot be null");

        }
        team1 = teamdao1.findById(team1.getId());
        kabaddiMatch.setTeam1(team1);
        if (team1 == null) {
            throw new IllegalArgumentException("Team 1 does not exist in the database");

        }
        Team team2 = kabaddiMatch.getTeam2();
        if (team2 == null) {
            throw new IllegalArgumentException("Team2 cannot be null");

        }
        team2 = teamdao1.findById(team2.getId());
        kabaddiMatch.setTeam2(team2);
        if (team2 == null) {
            throw new IllegalArgumentException("Team 2 does not exist in the database");
        }


        Tournament tournament = kabaddiMatch.getTournament();
        if(tournament!=null) {
            if (tournament.isIsdefault()) {
                String email = tournament.getUser().getEmail();

                Tournament defaulttournament = td.findAllTournamentsByUserEmail(email, tournament.getGame());

                if (defaulttournament != null) {
                    kabaddiMatch.setTournament(defaulttournament);
                } else {
                    throw new IllegalStateException("No default tournament found for the user");
                }

            } else {

                tournament = td.findById(tournament.getId());
           kabaddiMatch.setTournament(tournament);

                if (tournament == null) {
                    throw new IllegalArgumentException("Tournament does not exist in the database");
                }

            }
        }else {
            throw new IllegalArgumentException("Tournament cannot be null");
        }
        kabaddimatchdao.save(kabaddiMatch);
        return kabaddiMatch;

    }

    @Override
    public List<KabaddiMatch> getKMatches() {
        return kabaddimatchdao.findAll();
    }

    @Override
    public BadmintonMatch createBadmintonMatch(BadmintonMatch badmintonMatch) {

        if (badmintonMatch == null) {
            throw new IllegalArgumentException("BadmintonMatch object cannot be null");
        }
        Team team1 = badmintonMatch.getTeam1();
        if (team1 == null) {
            throw new IllegalArgumentException("Team1 cannot be null");

        }
        team1 = teamdao1.findById(team1.getId());
        badmintonMatch.setTeam1(team1);
        if (team1 == null) {
            throw new IllegalArgumentException("Team 1 does not exist in the database");

        }
        Team team2 = badmintonMatch.getTeam2();
        if (team2 == null) {
            throw new IllegalArgumentException("Team2 cannot be null");

        }
        team2 = teamdao1.findById(team2.getId());
        badmintonMatch.setTeam2(team2);
        if (team2 == null) {
            throw new IllegalArgumentException("Team 2 does not exist in the database");
        }


        Tournament tournament = badmintonMatch.getTournament();
        if(tournament!=null) {
            if (tournament.isIsdefault()) {
                String email = tournament.getUser().getEmail();

                Tournament defaulttournament = td.findAllTournamentsByUserEmail(email, tournament.getGame());

                if (defaulttournament != null) {
                    badmintonMatch.setTournament(defaulttournament);
                } else {
                    throw new IllegalStateException("No default tournament found for the user");
                }

            } else {

                tournament = td.findById(tournament.getId());
                badmintonMatch.setTournament(tournament);

                if (tournament == null) {
                    throw new IllegalArgumentException("Tournament does not exist in the database");
                }

            }
        }else {
            throw new IllegalArgumentException("Tournament cannot be null");
        }
        badMintonMatchdao.save(badmintonMatch);
        return badmintonMatch;

    }
    @Override
    public void deletetournament(int id) {
        Tournament tournament=td.getReferenceById(id);
         tournament.setUser(null);
          td.delete(tournament);
    }

    @Override
    public void deleteteam(int id) {
        Team team=teamdao1.getReferenceById(id);
        team.setTournament(null);
        teamdao1.delete(team);
    }

    @Override
    public void deleteplayer(int id) {
        Player player=pd.getReferenceById(id);
        player.setTeam(null);
        pd.delete(player);
    }

    @Override
    public void deleteBadmintonMatch(int id) {
        BadmintonMatch badmintonMatch=badMintonMatchdao.getReferenceById(id);
        badmintonMatch.setTeam1(null);
        badmintonMatch.setTeam2(null);
        badmintonMatch.setTournament(null);
        badMintonMatchdao.delete(badmintonMatch);
    }

    @Override
    public void deleteTeamPlayers(int id) {
        Team team=teamdao1.getReferenceById(id);
        team.setTournament(null);
        pd.deleteByTeamId(id);
        teamdao1.delete(team);
    }

    @Override
    public List<BadmintonMatch> getMatchesForTournament(int id) {
        return badMintonMatchdao.findBadmintonMatchByTournamentId(id);
    }

    @Override
    public Tournament updateTournament(Tournament tournament) {
        User existingUser = sd.findByEmail(tournament.getUser().getEmail());

        if (existingUser == null) {
            User newUser = new User();
            newUser.setEmail(tournament.getUser().getEmail());
            sd.save(newUser);
            tournament.setUser(newUser);
        } else {
            tournament.setUser(existingUser);
        }
        td.save(tournament);
        return tournament;
    }

    @Override
    public Team updateTeam(Team team) {
        Tournament tournaments=td.findById(team.getTournament().getId());
        if(tournaments==null){
            Tournament tournaments1=new Tournament();
            tournaments1.setId(team.getTournament().getId());
            td.save(tournaments1);
            team.setTournament(tournaments1);
        }
        else
        {
            team.setTournament(tournaments);
        }
        if(team.getTournament()==null) {
            throw new IllegalArgumentException("null");
        }
        teamdao1.save(team);

        return team;
    }

    @Override
    public Player updatePlayer(Player player) {
        Team team=teamdao1.findById(player.getTeam().getId());
        if(team==null){
            Team team1=new Team();
            team1.setId(player.getTeam().getId());
            teamdao1.save(team1);
            player.setTeam(team1);

        }
        else {
            player.setTeam(team);
        }
        if (player.getTeam()==null){
            throw new IllegalArgumentException("null");
        }
        pd.save(player);
        return player;
    }

    @Override
    public BadmintonMatch updateBadmintonMatch(BadmintonMatch badmintonMatch) {
        if (badmintonMatch == null) {
            throw new IllegalArgumentException("BadmintonMatch object cannot be null");
        }
        Team team1 = badmintonMatch.getTeam1();
        if (team1 == null) {
            throw new IllegalArgumentException("Team1 cannot be null");

        }
        team1=teamdao1.findById(team1.getId());
        badmintonMatch.setTeam1(team1);
        if (team1 == null) {
            throw new IllegalArgumentException("Team 1 does not exist in the database");

        }
        Team team2 = badmintonMatch.getTeam2();
        if (team2 == null) {
            throw new IllegalArgumentException("Team2 cannot be null");

        }
        team2=teamdao1.findById(team2.getId());
        badmintonMatch.setTeam2(team2);
        if (team2 == null) {
            throw new IllegalArgumentException("Team 2 does not exist in the database");
        }


        Tournament tournament = badmintonMatch.getTournament();
        if (tournament == null) {
            throw new IllegalArgumentException("Tournament cannot be null");
        }
        tournament=td.findById(tournament.getId());
        badmintonMatch.setTournament(tournament);

        if (tournament == null) {
            throw new IllegalArgumentException("Tournament does not exist in the database");
        }
        badMintonMatchdao.save(badmintonMatch);
        return badmintonMatch;

    }
}






