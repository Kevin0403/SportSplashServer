package com.example.sportsplash.websocket.match;


import com.example.sportsplash.sports.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EnableAutoConfiguration
public class UploadScore {
    int updateTeam;
    int team1Score;
    int team2Score;
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

    public void updateKabaddiScore(KabaddiMatch match, int points, String type) {
        if (status != MatchStatus.ONGOING) {
            setTeam1Score(match.getTeam1score());
            setTeam2Score(match.getTeam2score());
            setWinner(match.getWinner());
            return;
        }

        if (updateTeam == 1) {
            if (type.equals("raid")) {
                match.setTeam1RaidPoints(match.getTeam1RaidPoints() + points);
            } else if (type.equals("tackle")) {
                match.setTeam1TacklePoints(match.getTeam1TacklePoints() + points);

            } else if (type.equals("allout")) {
                match.setTeam1AllOutPoints(match.getTeam1AllOutPoints() + points);
            } else if (type.equals("extra")) {
                match.setTeam1AllOutPoints(match.getTeam1AllOutPoints() + points);
            }
        } else {
            if (type.equals("raid")) {
                match.setTeam1RaidPoints(match.getTeam1RaidPoints() + points);
            } else if (type.equals("tackle")) {
                match.setTeam1TacklePoints(match.getTeam1TacklePoints() + points);

            } else if (type.equals("allout")) {
                match.setTeam1AllOutPoints(match.getTeam1AllOutPoints() + points);
            } else if (type.equals("extra")) {
                match.setTeam1AllOutPoints(match.getTeam1AllOutPoints() + points);
            }
        }
        setTeam1Score(match.getTeam1RaidPoints() + match.getTeam1TacklePoints() + match.getTeam1ExtraPoints() + match.getTeam1AllOutPoints());
        setTeam2Score(match.getTeam2AllOutPoints() + match.getTeam2RaidPoints() + match.getTeam2ExtraPoints() + match.getTeam2TacklePoints());

        if (team1Score > team2Score) {
            winner = match.getTeam1();
            match.setStatus(MatchStatus.COMPLETED);
            setStatus(MatchStatus.COMPLETED);
            match.setWinner(match.getTeam1());
        } else if (team1Score < team2Score) {
            winner = match.getTeam2();
            match.setStatus(MatchStatus.COMPLETED);
            setStatus(MatchStatus.COMPLETED);
            match.setWinner(match.getTeam2());
        } else {
            winner = null;
            match.setTie(true);
            match.setStatus(MatchStatus.COMPLETED);
            setStatus(MatchStatus.COMPLETED);
            match.setWinner(null);
        }
    }

    public void startMatch(Game game) {
        this.status = MatchStatus.ONGOING;
        this.team1Score = 0;
        this.team2Score = 0;

        switch (game) {
            case BADMINTON:
                BadmintonMatch badmintonMatch = new BadmintonMatch();
                setTeam1Score(0);
                setTeam2Score(0);
                badmintonMatch.setTeam1score(0);
                badmintonMatch.setTeam2score(0);
                badmintonMatch.setStartDate(new Date().toString());

                break;
            case KABADDI:
                KabaddiMatch kabaddiMatch = new KabaddiMatch();
                setTeam1Score(0);
                setTeam2Score(0);
                kabaddiMatch.setTeam1score(0);
                kabaddiMatch.setTeam2score(0);
                kabaddiMatch.setStartTime(new Date().toString());
                break;
            default:
                break;
        }

    }

}