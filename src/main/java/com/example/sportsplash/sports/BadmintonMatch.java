package com.example.sportsplash.sports;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.List;


@Entity
public class BadmintonMatch {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int team1score = 0;
    int team2score = 0;


    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    private List<BadmintonMatchState> matchStates;


    public MatchStatus getStatus() {
        return status;
    }

    public void setStatus(MatchStatus status) {
        this.status = status;
    }

    MatchStatus status = MatchStatus.UPCOMING;

    public int getRequiredScore() {
        return requiredScore;
    }

    int requiredScore = 0;

    public void setRequiredScore(int requiredScore) {
        this.requiredScore = requiredScore;
    }

    @ManyToOne
    @JoinColumn(name = "team1_fk",referencedColumnName = "id")
   private Team team1;

    @ManyToOne
    @JoinColumn(name = "team2_fk",referencedColumnName = "id")
   private Team team2;

    @ManyToOne
    @JoinColumn(name = "winner_fk",referencedColumnName = "id")
    private Team winner;
    String startTime;

    public Team getWinner() {
        return winner;
    }

    public void setWinner(Team winner) {
        this.winner = winner;
    }

    String endTime;



    @ManyToOne
    private  Tournament tournament;




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



    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {

        this.tournament = tournament;
    }

    public BadmintonMatch(int id, int team1score, int team2score, MatchStatus status, int requiredScore, Team team1, Team team2, Team winner, String startTime, String endTime, Tournament tournament) {
        this.id = id;
        this.team1score = team1score;
        this.team2score = team2score;
        this.status = status;
        this.requiredScore = requiredScore;
        this.team1 = team1;
        this.team2 = team2;
        this.winner = winner;
        this.startTime = startTime;
        this.endTime = endTime;
        this.tournament = tournament;
    }

    @Override
    public String toString() {
        return "BadmintonMatch{" +
                "id=" + id +
                ", team1score=" + team1score +
                ", team2score=" + team2score +
                ", status=" + status +
                ", requiredScore=" + requiredScore +
                ", team1=" + team1 +
                ", team2=" + team2 +
                ", winner=" + winner +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", tournament=" + tournament +
                '}';
    }

    public BadmintonMatch(int id) {
        this.id = id;
    }
}
