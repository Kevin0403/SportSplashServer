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

    @ManyToOne
    private  Tournament tournament;

    MatchStatus status = MatchStatus.UPCOMING;
    boolean tie=false;
    int raidPoints1;
    int raidPoints2;
    int tacklePoints1;
    int tacklePoints2;
    int  bonusPoints1;
    int bonusPoints2;
    int alloutPoints1;
    int alloutPoints2;
    int technicalPoints1;
    int technicalPoints2;



    public int getRaidPoints1() {
        return raidPoints1;
    }

    public void setRaidPoints1(int raidPoints1) {
        this.raidPoints1 = raidPoints1;
    }

    public int getRaidPoints2() {
        return raidPoints2;
    }

    public void setRaidPoints2(int raidPoints2) {
        this.raidPoints2 = raidPoints2;
    }

    public int getTacklePoints1() {
        return tacklePoints1;
    }

    public void setTacklePoints1(int tacklePoints1) {
        this.tacklePoints1 = tacklePoints1;
    }

    public int getTacklePoints2() {
        return tacklePoints2;
    }

    public void setTacklePoints2(int tacklePoints2) {
        this.tacklePoints2 = tacklePoints2;
    }

    public int getBonusPoints1() {
        return bonusPoints1;
    }

    public void setBonusPoints1(int bonusPoints1) {
        this.bonusPoints1 = bonusPoints1;
    }

    public int getBonusPoints2() {
        return bonusPoints2;
    }

    public void setBonusPoints2(int bonusPoints2) {
        this.bonusPoints2 = bonusPoints2;
    }

    public int getAlloutPoints1() {
        return alloutPoints1;
    }

    public void setAlloutPoints1(int alloutPoints1) {
        this.alloutPoints1 = alloutPoints1;
    }

    public int getAlloutPoints2() {
        return alloutPoints2;
    }

    public void setAlloutPoints2(int alloutPoints2) {
        this.alloutPoints2 = alloutPoints2;
    }

    public int getTechnicalPoints1() {
        return technicalPoints1;
    }

    public void setTechnicalPoints1(int technicalPoints1) {
        this.technicalPoints1 = technicalPoints1;
    }

    public int getTechnicalPoints2() {
        return technicalPoints2;
    }

    public void setTechnicalPoints2(int technicalPoints2) {
        this.technicalPoints2 = technicalPoints2;
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

    public KabaddiMatch(int id, int team1score, int team2score, Team team1, Team team2, Team winner, String startTime, String endTime, Tournament tournament, MatchStatus status, boolean tie, int raidPoints1, int raidPoints2, int tacklePoints1, int tacklePoints2, int bonusPoints1, int bonusPoints2, int alloutPoints1, int alloutPoints2, int technicalPoints1, int technicalPoints2) {
        this.id = id;
        this.team1score = team1score;
        this.team2score = team2score;
        this.team1 = team1;
        this.team2 = team2;
        this.winner = winner;
        this.startTime = startTime;
        this.endTime = endTime;
        this.tournament = tournament;
        this.status = status;
        this.tie = tie;
        this.raidPoints1 = raidPoints1;
        this.raidPoints2 = raidPoints2;
        this.tacklePoints1 = tacklePoints1;
        this.tacklePoints2 = tacklePoints2;
        this.bonusPoints1 = bonusPoints1;
        this.bonusPoints2 = bonusPoints2;
        this.alloutPoints1 = alloutPoints1;
        this.alloutPoints2 = alloutPoints2;
        this.technicalPoints1 = technicalPoints1;
        this.technicalPoints2 = technicalPoints2;
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
                ", tournament=" + tournament +
                ", status=" + status +
                ", tie=" + tie +
                ", raidPoints1=" + raidPoints1 +
                ", raidPoints2=" + raidPoints2 +
                ", tacklePoints1=" + tacklePoints1 +
                ", tacklePoints2=" + tacklePoints2 +
                ", bonusPoints1=" + bonusPoints1 +
                ", bonusPoints2=" + bonusPoints2 +
                ", alloutPoints1=" + alloutPoints1 +
                ", alloutPoints2=" + alloutPoints2 +
                ", technicalPoints1=" + technicalPoints1 +
                ", technicalPoints2=" + technicalPoints2 +
                '}';
    }
}
