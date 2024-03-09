package com.example.sportsplash.websocket.match;
import com.example.sportsplash.sports.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import java.util.Date;
import java.util.Stack;

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
    private Stack<MatchState> previousMatchStates = new Stack<>();


    public void updateBadmintonScore(BadmintonMatch match, boolean undo) {

        if (undo) {
            undoScore(match);
            return;
        }

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


        previousMatchStates.push( new MatchState(
                match.getTeam1score(), match.getTeam2score(),
                match.getStatus(), match.getRequiredScore(), match.getEndTime(),match.getWinner())
        );
    }

    private void undoScore(BadmintonMatch match) {
        if (!previousMatchStates.isEmpty()) {
            MatchState previousState = previousMatchStates.pop();
            match.setTeam1score(previousState.getTeam1Score());
            match.setTeam2score(previousState.getTeam2Score());
            match.setWinner(previousState.getWinner());
            match.setStatus(previousState.getStatus());
            match.setRequiredScore(previousState.getRequiredScore());
            match.setEndTime(previousState.getEndTime());
        }
    }

    public void startBadmintonMatch(BadmintonMatch badmintonMatch) {
        badmintonMatch.setStatus(MatchStatus.ONGOING);
        setStatus(MatchStatus.ONGOING);
        badmintonMatch.setTeam1score(0);
        setTeam1Score(0);
        badmintonMatch.setTeam2score(0);
        setTeam2Score(0);
        badmintonMatch.setStartTime(new Date().toString());



    }
}