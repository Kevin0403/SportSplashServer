package com.example.sportsplash.websocket.match;
import com.example.sportsplash.dao.BadmintonMatchStatusDao;
import com.example.sportsplash.sports.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Optional;
import java.util.Stack;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UploadBadmintonScore {

    BadmintonMatchState matchState;

    int updateTeam;
    int team1Score=0;
    int team2Score=0;
    Team winner;
    MatchStatus status;
    Boolean undo = false;


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

        if (team1Score >= match.getRequiredScore() || team2Score >= match.getRequiredScore()) {
            if (Math.abs(team2Score - team1Score) < 2) {
                match.setRequiredScore(match.getRequiredScore() + 1);
            }
            if (team1Score >= match.getRequiredScore()) {
                winner = match.getTeam1();
                match.setStatus(MatchStatus.COMPLETED);
                match.setEndTime(new Date().toString());
                setStatus(MatchStatus.COMPLETED);
                match.setWinner(match.getTeam1());
            }
            if (team2Score >= match.getRequiredScore()) {
                winner = match.getTeam2();
                match.setStatus(MatchStatus.COMPLETED);
                match.setEndTime(new Date().toString());
                setStatus(MatchStatus.COMPLETED);
                match.setWinner(match.getTeam2());
            }
        }


        BadmintonMatchState badmintonMatchState = new BadmintonMatchState();
        badmintonMatchState.setMatch(match);
        badmintonMatchState.setTeam1Score(team1Score);
        badmintonMatchState.setTeam2Score(team2Score);
        badmintonMatchState.setStatus(status);
        badmintonMatchState.setRequiredScore(match.getRequiredScore());
        setMatchState(badmintonMatchState);
    }

    public void undoScore(BadmintonMatch match, BadmintonMatchState state) {
        if(state != null){
            // updates in match
            match.setStatus(state.getStatus());
            match.setRequiredScore(state.getRequiredScore());
            match.setTeam1score(state.getTeam1Score());
            match.setTeam2score(state.getTeam2Score());

            // updates in current variables
            setStatus(state.getStatus());
            setTeam1Score(state.getTeam1Score());
            setTeam2Score(state.getTeam2Score());
        }
    }

    public void startBadmintonMatch(BadmintonMatch badmintonMatch) {

        badmintonMatch.setTeam1score(0);
        setTeam1Score(0);
        badmintonMatch.setTeam2score(0);
        setTeam2Score(0);
        badmintonMatch.setStartTime(new Date().toString());

        BadmintonMatchState badmintonMatchState = new BadmintonMatchState();
        badmintonMatchState.setMatch(badmintonMatch);
        badmintonMatchState.setTeam1Score(team1Score);
        badmintonMatchState.setTeam2Score(team2Score);
        badmintonMatchState.setStatus(MatchStatus.UPCOMING);
        badmintonMatchState.setRequiredScore(badmintonMatchState.getRequiredScore());
        setMatchState(badmintonMatchState);

        badmintonMatch.setStatus(MatchStatus.ONGOING);
        setStatus(MatchStatus.ONGOING);
    }
}