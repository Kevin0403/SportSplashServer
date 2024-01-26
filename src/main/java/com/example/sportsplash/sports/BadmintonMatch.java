package com.example.sportsplash.sports;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class BadmintonMatch {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int team1score;
    int team2score;
    @ManyToOne
    @JoinColumn(name = "team1_fk",referencedColumnName = "id")
   private Team team1;

    @ManyToOne
    @JoinColumn(name = "team2_fk",referencedColumnName = "id")
   private Team team2;
    String startTime;
    String endTime;

    String startDate;
    String endDate;

    @ManyToOne
    private  Tournament tournament;


    public BadmintonMatch(int id, int team1score, int team2score, Team team1, Team team2, String startTime, String endTime, String startDate, String endDate, Tournament tournament) {
        this.id = id;
        this.team1score = team1score;
        this.team2score = team2score;
        this.team1 = team1;
        this.team2 = team2;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tournament = tournament;
    }

    public BadmintonMatch() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeam1score() {
        return team1score;
    }

    public void setTeam1score(int team1score) {
        this.team1score = team1score;
    }

    public int getTeam2score() {
        return team2score;
    }

    public void setTeam2score(int team2score) {
        this.team2score = team2score;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }


    @Override
    public String toString() {
        return "BadmintonMatch{" +
                "id=" + id +
                ", team1score=" + team1score +
                ", team2score=" + team2score +
                ", team1=" + team1 +
                ", team2=" + team2 +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", tournament=" + tournament +
                '}';
    }

    public BadmintonMatch(int id) {
        this.id = id;
    }
}
