package com.example.sportsplash.sports;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class BadmintonMatch {
    @Id
    int id;
    int team1id;

    public BadmintonMatch(int id, int team1id, int team2id, int team1score, int team2score, String startTime, String endTime, Tournament tournament, List<Team> teamList) {
        this.id = id;
        this.team1id = team1id;
        this.team2id = team2id;
        this.team1score = team1score;
        this.team2score = team2score;
        this.startTime = startTime;
        this.endTime = endTime;
        this.tournament = tournament;
        this.teamList = teamList;
    }


    int team2id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeam1id() {
        return team1id;
    }

    public void setTeam1id(int team1id) {
        this.team1id = team1id;
    }

    public int getTeam2id() {
        return team2id;
    }

    public void setTeam2id(int team2id) {
        this.team2id = team2id;
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

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

    int team1score;
    int team2score;
    String startTime;
    String endTime;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "tournament_fk",referencedColumnName = "id")
    private  Tournament tournament;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Team> teamList;

    public BadmintonMatch() {
        super();
    }

    public BadmintonMatch(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BadmintonMatch{" +
                "id=" + id +
                ", team1id=" + team1id +
                ", team2id=" + team2id +
                ", team1score=" + team1score +
                ", team2score=" + team2score +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", tournament=" + tournament +
                ", teamList=" + teamList +
                '}';
    }
}
