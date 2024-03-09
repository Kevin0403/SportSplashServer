package com.example.sportsplash.websocket.match;
import com.example.sportsplash.sports.MatchStatus;
import com.example.sportsplash.sports.Team;

public class MatchState {
    private int team1Score;
    private int team2Score;
    private Team winner;
    private MatchStatus status;
    private int requiredScore;
    private String endTime;

    // Constructors
    public MatchState(int team1Score, int team2Score, Team winner, MatchStatus status, int requiredScore, String endTime) {
        this.team1Score = team1Score;
        this.team2Score = team2Score;
        this.winner = winner;
        this.status = status;
        this.requiredScore = requiredScore;
        this.endTime = endTime;
    }

    // Getters and setters
    public int getTeam1Score() {
        return team1Score;
    }

    public void setTeam1Score(int team1Score) {
        this.team1Score = team1Score;
    }

    public int getTeam2Score() {
        return team2Score;
    }

    public void setTeam2Score(int team2Score) {
        this.team2Score = team2Score;
    }

    public Team getWinner() {
        return winner;
    }

    public void setWinner(Team winner) {
        this.winner = winner;
    }

    public MatchStatus getStatus() {
        return status;
    }

    public void setStatus(MatchStatus status) {
        this.status = status;
    }

    public int getRequiredScore() {
        return requiredScore;
    }

    public void setRequiredScore(int requiredScore) {
        this.requiredScore = requiredScore;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
