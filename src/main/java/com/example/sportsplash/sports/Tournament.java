package com.example.sportsplash.sports;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Tournament {

    @Id@GeneratedValue(strategy=GenerationType.IDENTITY)
    int id;
    String tournamentName;
    String game;
    String startDate;
    String endDate;
    int teamSize;
    int teams;
    @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name="email_FK",referencedColumnName = "email")
     private User user;


    public int getId() {
        return id;
    }
   
    public void setId(int id) {
        this.id = id;
    }

    public Tournament() {
        super();


    }



    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }

    public Tournament(int id, String tournamentName, String game, String startDate, String endDate, int teamSize, int teams, User user) {
        this.id =id;
        this.tournamentName = tournamentName;
        this.game = game;
        this.startDate = startDate;
        this.endDate = endDate;
        this.teamSize = teamSize;
        this.teams = teams;
        this.user = user;
    }


    public int getTeams() {

        return teams;
    }

    public Tournament(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "id=" + id +
                ", tournamentName='" + tournamentName + '\'' +
                ", game='" + game + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", teamSize=" + teamSize +
                ", teams=" + teams +
                ", user=" + user +
                '}';
    }

    public User getUser() {

        if (user == null) {
            user = new User();  // Create a new User object if it's null
        }
        return user;

    }

    public void setUser(User user) {

        this.user = user;
    }

    public void setTeams(int teams) {

        this.teams = teams;
    }



}
