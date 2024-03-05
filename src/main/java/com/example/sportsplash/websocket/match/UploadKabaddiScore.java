package com.example.sportsplash.websocket.match;

import com.example.sportsplash.sports.KabaddiMatch;
import com.example.sportsplash.sports.MatchStatus;
import com.example.sportsplash.sports.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EnableAutoConfiguration
public class UploadKabaddiScore {

    int updateTeam;
    int team1Score;
    int team2Score;
    int  raidPoints1;
    int raidPoints2;
    int tacklePoints1;
    int tacklePoints2;
    int  extraPoints1;
    int extraPoints2;
    int alloutPoints1;
    int alloutPoints2;
    Team winner;
    MatchStatus status;



    public void updateKabaddiScore(KabaddiMatch match, int points, String type) {
        if (status != MatchStatus.ONGOING) {
            setTeam1Score(match.getTeam1score());
            setTeam2Score(match.getTeam2score());
            setRaidPoints1(match.getTeam1RaidPoints());
            setRaidPoints2(match.getTeam2RaidPoints());
            setTacklePoints1(match.getTeam1TacklePoints());
            setTacklePoints2(match.getTeam2RaidPoints());
            setExtraPoints1(match.getTeam1ExtraPoints());
            setExtraPoints2(match.getTeam2ExtraPoints());
            setAlloutPoints1(match.getTeam1AllOutPoints());
            setAlloutPoints2(match.getTeam2AllOutPoints());
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
             winner= match.getTeam1();
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

    public void startKabaddiMatch(KabaddiMatch kabaddiMatch) {
        kabaddiMatch.setStatus(MatchStatus.ONGOING);
        setStatus(MatchStatus.ONGOING);
        kabaddiMatch.setTeam1score(0);
        setTeam1Score(0);
        kabaddiMatch.setTeam2score(0);
        setTeam2Score(0);
        kabaddiMatch.setTeam1RaidPoints(0);
        setRaidPoints1(0);
        kabaddiMatch.setTeam2RaidPoints(0);
        setRaidPoints2(0);
        kabaddiMatch.setTeam1TacklePoints(0);
        setTacklePoints1(0);
        kabaddiMatch.setTeam2TacklePoints(0);
        setTacklePoints2(0);
        kabaddiMatch.setTeam1AllOutPoints(0);
        setAlloutPoints1(0);
        kabaddiMatch.setTeam2AllOutPoints(0);
        setAlloutPoints2(0);
        kabaddiMatch.setTeam1ExtraPoints(0);
        setExtraPoints1(0);
        kabaddiMatch.setTeam2ExtraPoints(0);
        setExtraPoints2(0);
        kabaddiMatch.setMatchDate(new Date().toString());



    }
}
