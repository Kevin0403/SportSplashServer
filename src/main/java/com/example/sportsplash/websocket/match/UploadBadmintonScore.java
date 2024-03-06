package com.example.sportsplash.websocket.match;


import com.example.sportsplash.sports.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UploadBadmintonScore {
    int updateTeam;
    int team1Score=0;
    int team2Score=0;
    Team winner;
    MatchStatus status;


    public void updateBadmintonScore(BadmintonMatch match) {
        if (status != MatchStatus.ONGOING) {
            setTeam1Score(match.getTeam1score());
            setTeam2Score(match.getTeam2score());
            setWinner(match.getWinner());
            return;
        }
        if (updateTeam == 1) {
            match.setTeam1score(match.getTeam1score() + 1);
        } else {
            match.setTeam2score(match.getTeam2score() + 1);
        }
        setTeam1Score(match.getTeam1score());
        setTeam2Score(match.getTeam2score());
        if (team1Score >= match.getRequiredScore()) {
            winner = match.getTeam1();
            match.setStatus(MatchStatus.COMPLETED);
            match.setEndDate(new Date().toString());
            setStatus(MatchStatus.COMPLETED);
            match.setWinner(match.getTeam1());
        }
        if (team2Score >= match.getRequiredScore()) {
            winner = match.getTeam2();
            match.setStatus(MatchStatus.COMPLETED);
            match.setEndDate(new Date().toString());
            setStatus(MatchStatus.COMPLETED);
            match.setWinner(match.getTeam2());
        }

    }



    public void startBadmintonMatch(BadmintonMatch badmintonMatch) {
        badmintonMatch.setStatus(MatchStatus.ONGOING);
        setStatus(MatchStatus.ONGOING);
        badmintonMatch.setTeam1score(0);
        setTeam1Score(0);
        badmintonMatch.setTeam2score(0);
        setTeam2Score(0);
        badmintonMatch.setStartDate(new Date().toString());
        badmintonMatch.setEndDate(new Date().toString());


    }
}