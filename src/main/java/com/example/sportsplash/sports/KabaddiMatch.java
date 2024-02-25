package com.example.sportsplash.sports;

import jakarta.persistence.*;

@Entity
public class KabaddiMatch {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int team1score = 0;
    int team2score = 0;
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
    String endTime;
    String matchDate;
    @ManyToOne
    private  Tournament tournament;

    MatchStatus status = MatchStatus.UPCOMING;
    boolean tie=false;

    int team1RaidPoints;
    int team2RaidPoints;

    int team1TacklePoints;
    int team2TacklePoints;

    int team1ExtraPoints;

    int team2ExtraPoints;

    int team1AllOutPoints;
    int team2AllOutPoints;

    public int getTeam1RaidPoints() {
        return team1RaidPoints;
    }

    public void setTeam1RaidPoints(int team1RaidPoints) {
        this.team1RaidPoints = team1RaidPoints;
    }

    public int getTeam2RaidPoints() {
        return team2RaidPoints;
    }

    public void setTeam2RaidPoints(int team2RaidPoints) {
        this.team2RaidPoints = team2RaidPoints;
    }

    public int getTeam1TacklePoints() {
        return team1TacklePoints;
    }

    public void setTeam1TacklePoints(int team1TacklePoints) {
        this.team1TacklePoints = team1TacklePoints;
    }

    public int getTeam2TacklePoints() {
        return team2TacklePoints;
    }

    public void setTeam2TacklePoints(int team2TacklePoints) {
        this.team2TacklePoints = team2TacklePoints;
    }

    public int getTeam1ExtraPoints() {
        return team1ExtraPoints;
    }

    public void setTeam1ExtraPoints(int team1ExtraPoints) {
        this.team1ExtraPoints = team1ExtraPoints;
    }

    public int getTeam2ExtraPoints() {
        return team2ExtraPoints;
    }

    public void setTeam2ExtraPoints(int team2ExtraPoints) {
        this.team2ExtraPoints = team2ExtraPoints;
    }

    public int getTeam1AllOutPoints() {
        return team1AllOutPoints;
    }

    public void setTeam1AllOutPoints(int team1AllOutPoints) {
        this.team1AllOutPoints = team1AllOutPoints;
    }

    public int getTeam2AllOutPoints() {
        return team2AllOutPoints;
    }

    public void setTeam2AllOutPoints(int team2AllOutPoints) {
        this.team2AllOutPoints = team2AllOutPoints;
    }

    public KabaddiMatch() {
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

    public Team getWinner() {
        return winner;
    }

    public void setWinner(Team winner) {
        this.winner = winner;
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

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public MatchStatus getStatus() {
        return status;
    }

    public void setStatus(MatchStatus status) {
        this.status = status;
    }

    public boolean isTie() {
        return tie;
    }

    public void setTie(boolean tie) {
        this.tie = tie;
    }

    public KabaddiMatch(int id) {
        this.id = id;
    }

    public KabaddiMatch(int id, int team1score, int team2score, Team team1, Team team2, Team winner, String startTime, String endTime, String matchDate, Tournament tournament, MatchStatus status, int team1RaidPoints, int team2RaidPoints, int team1TacklePoints, int team2TacklePoints, int team1ExtraPoints, int team2ExtraPoints, int team1AllOutPoints, int team2AllOutPoints) {
        this.id = id;
        this.team1score = team1score;
        this.team2score = team2score;
        this.team1 = team1;
        this.team2 = team2;
        this.winner = winner;
        this.startTime = startTime;
        this.endTime = endTime;
        this.matchDate = matchDate;
        this.tournament = tournament;
        this.status = status;
        this.team1RaidPoints = team1RaidPoints;
        this.team2RaidPoints = team2RaidPoints;
        this.team1TacklePoints = team1TacklePoints;
        this.team2TacklePoints = team2TacklePoints;
        this.team1ExtraPoints = team1ExtraPoints;
        this.team2ExtraPoints = team2ExtraPoints;
        this.team1AllOutPoints = team1AllOutPoints;
        this.team2AllOutPoints = team2AllOutPoints;
    }

    @Override
    public String toString() {
        return "KabaddiMatch{" +
                "id=" + id +
                ", team1score=" + team1score +
                ", team2score=" + team2score +
                ", team1=" + team1 +
                ", team2=" + team2 +
                ", winner=" + winner +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", matchDate='" + matchDate + '\'' +
                ", tournament=" + tournament +
                ", status=" + status +
                ", tie=" + tie +
                ", team1RaidPoints=" + team1RaidPoints +
                ", team2RaidPoints=" + team2RaidPoints +
                ", team1TacklePoints=" + team1TacklePoints +
                ", team2TacklePoints=" + team2TacklePoints +
                ", team1ExtraPoints=" + team1ExtraPoints +
                ", team2ExtraPoints=" + team2ExtraPoints +
                ", team1AllOutPoints=" + team1AllOutPoints +
                ", team2AllOutPoints=" + team2AllOutPoints +
                '}';
    }
}
