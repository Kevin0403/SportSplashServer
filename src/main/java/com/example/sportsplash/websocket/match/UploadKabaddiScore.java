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
    int team1score=0;
    int team2score=0;
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
                match.setRaidPoints1(match.getTacklePoints1() + raidPoints1);
                match.setTeam1score(match.getTeam1score()+raidPoints1);
            }
            else if (tacklePoints1!=0) {
                match.setRaidPoints2(match.getRaidPoints2() + tacklePoints1);
                match.setTeam1score(match.getTeam1score()+tacklePoints1);

            } else if (alloutPoints1!=0) {
                match.setAlloutPoints1(match.getAlloutPoints1() + alloutPoints1);
                match.setTeam1score(match.getTeam1score()+ alloutPoints1);
            } else if (bonusPoints1!=0) {
                match.setBonusPoints1(match.getBonusPoints1() + bonusPoints1);
                match.setTeam1score(match.getTeam1score()+ bonusPoints1);
            }
            else if(technicalPoints1!=0)
            {
                match.setTechnicalPoints1(match.getTechnicalPoints1()+ technicalPoints1);
                match.setTeam1score(match.getTeam1score()+ technicalPoints1);
            }

        } else {
            if(raidPoints2!=0){
                match.setRaidPoints2(match.getRaidPoints2() + raidPoints2);
                match.setTeam2score(match.getTeam2score()+ raidPoints2);

            }
            else if(tacklePoints2!=0){
                match.setTacklePoints2(match.getTacklePoints2()+tacklePoints2 );
                match.setTeam2score(match.getTeam2score()+ tacklePoints2);

            }
            else if(alloutPoints2!=0){
                match.setAlloutPoints2(match.getAlloutPoints2()+ alloutPoints2);
                match.setTeam2score(match.getTeam2score()+ alloutPoints2);
            }
            else if (bonusPoints2!=0) {
                match.setBonusPoints2(match.getBonusPoints2()+bonusPoints2);
                match.setTeam2score(match.getTeam2score()+ bonusPoints2);

            }
            else if(technicalPoints2!=0){
                match.setTechnicalPoints2(match.getTechnicalPoints2()+ technicalPoints2);
                match.setTeam2score(match.getTeam2score()+ technicalPoints2);
            }
        }
       setTeam1score(match.getTeam1score());
        setTeam2score(match.getTeam2score());
        if (team1score > team2score) {
             winner= match.getTeam1();
            match.setStatus(MatchStatus.COMPLETED);
            setStatus(MatchStatus.COMPLETED);
            match.setWinner(match.getTeam1());
        } else if (team1score < team2score) {
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
       setTeam1score(0);
        kabaddiMatch.setTeam2score(0);
        setTeam2score(0);
        kabaddiMatch.setRaidPoints1(0);
        setRaidPoints1(0);
        kabaddiMatch.setRaidPoints2(0);
        setRaidPoints2(0);
        kabaddiMatch.setTacklePoints1(0);
        setTacklePoints1(0);
        kabaddiMatch.setTacklePoints2(0);
        setTacklePoints2(0);
        kabaddiMatch.setAlloutPoints1(0);
        setAlloutPoints1(0);
        kabaddiMatch.setAlloutPoints2(0);
        setAlloutPoints2(0);
        kabaddiMatch.setBonusPoints1(0);
        setBonusPoints1(0);
        kabaddiMatch.setBonusPoints2(0);
        setBonusPoints2(0);
        kabaddiMatch.setMatchDate(new Date().toString());



    }


    public void endKabaddiMatch(KabaddiMatch match) {
        setTeam1score(match.getTeam1score());
       setTeam2score(match.getTeam2score());
        setRaidPoints1(match.getRaidPoints1());
        setRaidPoints2(match.getRaidPoints2());
        setTacklePoints1(match.getTacklePoints1());
        setTacklePoints2(match.getTacklePoints2());
        setBonusPoints1(match.getBonusPoints1());
        setBonusPoints2(match.getBonusPoints2());
        setAlloutPoints1(match.getAlloutPoints1());
        setAlloutPoints2(match.getAlloutPoints2());
        setWinner(match.getWinner());


    }
}
