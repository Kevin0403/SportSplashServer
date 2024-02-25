package com.example.sportsplash.sports;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
public class Tournament {

    @Id@GeneratedValue(strategy=GenerationType.IDENTITY)
    int id;

    String tournamentName;

    Game game;

    String startDate;

    String endDate;

    int teamSize;

    int teams;

    boolean isdefault=false;

    @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name="email_FK",referencedColumnName = "email")
     private User user;

    public Tournament(int id, String tournamentName, Game game, String startDate, String endDate, int teamSize, int teams, boolean isdefault, User user) {
        this.id = id;
        this.tournamentName = tournamentName;
        this.game = game;
        this.startDate = startDate;
        this.endDate = endDate;
        this.teamSize = teamSize;
        this.teams = teams;
        this.isdefault = isdefault;
        this.user = user;
    }

    public Tournament() {
        super();
    }


    @Override
    public String toString() {
        return "Tournament{" +
                "id=" + id +
                ", tournamentName='" + tournamentName + '\'' +
                ", game=" + game +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", teamSize=" + teamSize +
                ", teams=" + teams +
                ", isdefault=" + isdefault +
                ", user=" + user +
                '}';
    }

    public User getUser() {

        if (user == null) {
            user = new User();
        }
        return user;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
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

    public int getTeams() {
        return teams;
    }

    public void setTeams(int teams) {
        this.teams = teams;
    }

    public boolean isIsdefault() {
        return isdefault;
    }

    public void setIsdefault(boolean isdefault) {
        this.isdefault = isdefault;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
