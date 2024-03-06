package com.example.sportsplash.websocket.match;

import com.example.sportsplash.sports.KabaddiMatch;
import com.example.sportsplash.sports.MatchStatus;
import com.example.sportsplash.sports.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UploadKabaddiScore {

    int updateTeam;
    int team1Score=0;
    int team2Score=0;
    int raidPoints1=0;
    int raidPoints2=0;
    int tacklePoints1=0;
    int tacklePoints2=0;
    int  bonusPoints1=0;
    int bonusPoints2=0;
    int alloutPoints1=0;
    int alloutPoints2=0;
    int technicalPoints1=0;
    int technicalPoints2=0;
    Team winner;
    MatchStatus status;
    int points=0;
   //PointType type;




    public void updateKabaddiScore(KabaddiMatch match) {


        if (updateTeam == 1) {
            if (raidPoints1!=0) {
                match.setTeam1RaidPoints(match.getTeam1RaidPoints() + raidPoints1);
                match.setTeam1score(match.getTeam1score()+raidPoints1);
            }
            else if (tacklePoints1!=0) {
                match.setTeam1TacklePoints(match.getTeam1TacklePoints() + tacklePoints1);
                match.setTeam1score(match.getTeam1score()+tacklePoints1);

            } else if (alloutPoints1!=0) {
                match.setTeam1AllOutPoints(match.getTeam1AllOutPoints() + alloutPoints1);
                match.setTeam1score(match.getTeam1score()+ alloutPoints1);
            } else if (bonusPoints1!=0) {
                match.setTeam1BonusPoints(match.getTeam1BonusPoints() + bonusPoints1);
                match.setTeam1score(match.getTeam1score()+ bonusPoints1);
            }
            else if(technicalPoints1!=0)
            {
                match.setTeam1TechnicalPoints(match.getTeam1TechnicalPoints()+ technicalPoints1);
                match.setTeam1score(match.getTeam1score()+ technicalPoints1);
            }

        } else {
            if(raidPoints2!=0){
                match.setTeam2RaidPoints(match.getTeam2RaidPoints() + raidPoints2);
                match.setTeam2score(match.getTeam2score()+ raidPoints2);

            }
            else if(tacklePoints2!=0){
                match.setTeam2TacklePoints(match.getTeam2TacklePoints()+tacklePoints2 );
                match.setTeam2score(match.getTeam2score()+ tacklePoints2);

            }
            else if(alloutPoints2!=0){
                match.setTeam2AllOutPoints(match.getTeam2AllOutPoints()+ alloutPoints2);
                match.setTeam2score(match.getTeam2score()+ alloutPoints2);
            }
            else if (bonusPoints2!=0) {
                match.setTeam2BonusPoints(match.getTeam2BonusPoints()+bonusPoints2);
                match.setTeam2score(match.getTeam2score()+ bonusPoints2);

            }
            else if(technicalPoints2!=0){
                match.setTeam2TechnicalPoints(match.getTeam2TechnicalPoints()+ technicalPoints2);
                match.setTeam2score(match.getTeam2score()+ technicalPoints2);
            }
        }
        setTeam1Score(match.getTeam1score());
        setTeam2Score(match.getTeam2score());
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
        kabaddiMatch.setTeam1BonusPoints(0);
        setBonusPoints1(0);
        kabaddiMatch.setTeam2BonusPoints(0);
        setBonusPoints2(0);
        kabaddiMatch.setMatchDate(new Date().toString());



    }


    public void endKabaddiMatch(KabaddiMatch match) {
        setTeam1Score(match.getTeam1score());
        setTeam2Score(match.getTeam2score());
        setRaidPoints1(match.getTeam1RaidPoints());
        setRaidPoints2(match.getTeam2RaidPoints());
        setTacklePoints1(match.getTeam1TacklePoints());
        setTacklePoints2(match.getTeam2RaidPoints());
        setBonusPoints1(match.getTeam1BonusPoints());
        setBonusPoints2(match.getTeam2BonusPoints());
        setAlloutPoints1(match.getTeam1AllOutPoints());
        setAlloutPoints2(match.getTeam2AllOutPoints());
        setWinner(match.getWinner());


    }
}
