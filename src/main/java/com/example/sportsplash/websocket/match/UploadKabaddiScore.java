package com.example.sportsplash.websocket.match;

import com.example.sportsplash.sports.KabaddiMatch;
import com.example.sportsplash.sports.KabaddiMatchState;
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

    boolean undo = false;
    KabaddiMatchState matchState = null;

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







    public void updateKabaddiScore(KabaddiMatch match) {
        updateMatchState(match);
        setStatus(match.getStatus());


        if (updateTeam == 1) {
            if (raidPoints1!=0) {
                match.setRaidPoints1(match.getTacklePoints1() + raidPoints1);
                match.setTeam1score(match.getTeam1score()+raidPoints1);
            }
            if (tacklePoints1!=0) {
                match.setRaidPoints1(match.getRaidPoints1() + tacklePoints1);
                match.setTeam1score(match.getTeam1score()+tacklePoints1);

            }
            if (alloutPoints1!=0) {
                match.setAlloutPoints1(match.getAlloutPoints1() + alloutPoints1);
                match.setTeam1score(match.getTeam1score()+ alloutPoints1);
            }
            if (bonusPoints1!=0) {
                match.setBonusPoints1(match.getBonusPoints1() + bonusPoints1);
                match.setTeam1score(match.getTeam1score()+ bonusPoints1);
            }
            if(technicalPoints1!=0)
            {
                match.setTechnicalPoints1(match.getTechnicalPoints1()+ technicalPoints1);
                match.setTeam1score(match.getTeam1score()+ technicalPoints1);
            }

        } else {
            if(raidPoints2!=0){
                match.setRaidPoints2(match.getRaidPoints2() + raidPoints2);
                match.setTeam2score(match.getTeam2score()+ raidPoints2);

            }
            if(tacklePoints2!=0){
                match.setTacklePoints2(match.getTacklePoints2()+tacklePoints2 );
                match.setTeam2score(match.getTeam2score()+ tacklePoints2);

            }
            if(alloutPoints2!=0){
                match.setAlloutPoints2(match.getAlloutPoints2()+ alloutPoints2);
                match.setTeam2score(match.getTeam2score()+ alloutPoints2);
            }
            if (bonusPoints2!=0) {
                match.setBonusPoints2(match.getBonusPoints2()+bonusPoints2);
                match.setTeam2score(match.getTeam2score()+ bonusPoints2);

            }
            if(technicalPoints2!=0){
                match.setTechnicalPoints2(match.getTechnicalPoints2()+ technicalPoints2);
                match.setTeam2score(match.getTeam2score()+ technicalPoints2);
            }
        }

        setTeam1score(match.getTeam1score());
        setTeam2score(match.getTeam2score());
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
        kabaddiMatch.setTechnicalPoints1(0);
        setTacklePoints2(0);
        kabaddiMatch.setTechnicalPoints2(0);
        setTacklePoints1(0);
        kabaddiMatch.setStartTime(new Date().toString());
        updateMatchState(kabaddiMatch);
        matchState.setStatus(MatchStatus.UPCOMING);

    }

    public void undoScore(KabaddiMatch match, KabaddiMatchState kabaddiMatchState){

        match.setStatus(kabaddiMatchState.getStatus());
        match.setTeam1score(kabaddiMatchState.getTeam1score());
        match.setTeam2score(kabaddiMatchState.getTeam2score());
        match.setTechnicalPoints2(kabaddiMatchState.getTechnicalPoints2());
        match.setTechnicalPoints1(kabaddiMatchState.getTechnicalPoints1());
        match.setTacklePoints1(kabaddiMatchState.getTacklePoints1());
        match.setTacklePoints2(kabaddiMatchState.getTacklePoints2());
        match.setRaidPoints1(kabaddiMatchState.getRaidPoints1());
        match.setRaidPoints2(kabaddiMatchState.getRaidPoints2());
        match.setBonusPoints1(kabaddiMatchState.getBonusPoints1());
        match.setBonusPoints2(kabaddiMatchState.getBonusPoints2());
        match.setAlloutPoints1(kabaddiMatchState.getAlloutPoints1());
        match.setAlloutPoints2(kabaddiMatchState.getAlloutPoints2());

        setStatus(kabaddiMatchState.getStatus());
        setTeam1score(kabaddiMatchState.getTeam1score());
        setTeam2score(kabaddiMatchState.getTeam2score());
        setTechnicalPoints2(kabaddiMatchState.getTechnicalPoints2());
        setTechnicalPoints1(kabaddiMatchState.getTechnicalPoints1());
        setTacklePoints1(kabaddiMatchState.getTacklePoints1());
        setTacklePoints2(kabaddiMatchState.getTacklePoints2());
        setRaidPoints1(kabaddiMatchState.getRaidPoints1());
        setRaidPoints2(kabaddiMatchState.getRaidPoints2());
        setBonusPoints1(kabaddiMatchState.getBonusPoints1());
        setBonusPoints2(kabaddiMatchState.getBonusPoints2());
        setAlloutPoints1(kabaddiMatchState.getAlloutPoints1());
        setAlloutPoints2(kabaddiMatchState.getAlloutPoints2());


    }

    public void updateMatchState(KabaddiMatch match){
        matchState = new KabaddiMatchState();
        matchState.setStatus(match.getStatus());
        matchState.setTeam1score(match.getTeam1score());
        matchState.setTeam2score(match.getTeam2score());
        matchState.setTechnicalPoints2(match.getTechnicalPoints2());
        matchState.setTechnicalPoints1(match.getTechnicalPoints1());
        matchState.setTacklePoints1(match.getTacklePoints1());
        matchState.setTacklePoints2(match.getTacklePoints2());
        matchState.setRaidPoints1(match.getRaidPoints1());
        matchState.setRaidPoints2(match.getRaidPoints2());
        matchState.setBonusPoints1(match.getBonusPoints1());
        matchState.setBonusPoints2(match.getBonusPoints2());
        matchState.setAlloutPoints1(match.getAlloutPoints1());
        matchState.setAlloutPoints2(match.getAlloutPoints2());
        matchState.setMatch(match);

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


        if(team1score > team2score){
            match.setWinner(match.getTeam1());
            setWinner(match.getWinner());
        }
        else {
            match.setWinner(match.getTeam2());
            setWinner(match.getTeam2());
        }
        match.setStatus(MatchStatus.COMPLETED);
        setStatus(MatchStatus.COMPLETED);
        match.setEndTime(new Date().toString());

    }
}
