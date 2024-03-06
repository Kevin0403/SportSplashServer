package com.example.sportsplash.websocket.match;

import com.example.sportsplash.dao.badmintonmatchdao;
import com.example.sportsplash.dao.kabaddimatchdao;
import com.example.sportsplash.service.sportsservice;
import com.example.sportsplash.sports.BadmintonMatch;
import com.example.sportsplash.sports.Game;
import com.example.sportsplash.sports.KabaddiMatch;
import com.example.sportsplash.sports.MatchStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Slf4j
@CrossOrigin("*")
public class MatchController {

    @Autowired
    private sportsservice service;
    @Autowired
    private badmintonmatchdao badMintonMatchdao;
    @Autowired
    private kabaddimatchdao kabaddimatchdao;

    @MessageMapping("/updateBadmintonScore/{matchId}")
    @SendTo("/public/badmintonScoreUpdates/{matchId}")
    public ResponseEntity<Object> updateBadmintonScore(
            @DestinationVariable("matchId") int matchId,
            @RequestBody UploadBadmintonScore score){
        BadmintonMatch match = service.getBadmintonMatch(matchId);
        score.updateBadmintonScore(match);
        badMintonMatchdao.save(match);
        return ResponseEntity.ok(score);
    }
    @MessageMapping("/updateKabaddiScore/{matchId}")
    @SendTo("/public/kabaddiScoreUpdates/{matchId}")
    public ResponseEntity<Object> updateKabaddiScore(
            @DestinationVariable("matchId") int matchId,
            @RequestBody UploadKabaddiScore score){
//        log.info("ERFdgfgddfgfdg");
//        System.out.println("!wweewrdewrrwrewrewrewrewr");
        KabaddiMatch match = service.getKabaddiMatch(matchId);
        score.updateKabaddiScore(match);
        kabaddimatchdao.save(match);
//        System.out.println("fgvbvbbbcbcvbbcvbcvcbvcb");
        return ResponseEntity.ok(score);
    }
    @MessageMapping("/startBadmintonMatch/{matchId}")
    @SendTo("/public/badmintonScoreUpdates/{matchId}")
    public ResponseEntity<BadmintonMatch> startBadmintonMatch(
            @DestinationVariable("matchId") int matchId,
            @RequestBody UploadBadmintonScore score) {

        BadmintonMatch badmintonMatch = service.getBadmintonMatch(matchId);

        if (badmintonMatch != null && score.getStatus() == MatchStatus.ONGOING) {
            score.startBadmintonMatch(badmintonMatch);
            badMintonMatchdao.save(badmintonMatch);
            return ResponseEntity.ok(badmintonMatch);
        } else {
            throw new IllegalArgumentException("Match is not started.");
        }
    }

    @MessageMapping("/startKabaddiMatch/{matchId}")
    @SendTo("/public/kabaddiScoreUpdates/{matchId}")
    public ResponseEntity<KabaddiMatch> startKabaddiMatch(
            @DestinationVariable("matchId") int matchId,
            @RequestBody UploadKabaddiScore score) {

        KabaddiMatch kabaddiMatch = service.getKabaddiMatch(matchId);

        if (kabaddiMatch != null && score.getStatus() == MatchStatus.ONGOING) {
//            log.info("dfggfdgd");
            score.startKabaddiMatch(kabaddiMatch);
           kabaddimatchdao.save(kabaddiMatch);
            return ResponseEntity.ok(kabaddiMatch);
        } else {
            throw new IllegalArgumentException("Match is not started.");
        }
    }

    @MessageMapping("/endKabaddiMatch/{matchId}")
    @SendTo("/public/scoreUpdates/{matchId}")
    public ResponseEntity<KabaddiMatch> endKabaddiMatch(@DestinationVariable("matchId") int matchId,
                                                        @RequestBody UploadKabaddiScore score){
        KabaddiMatch kabaddiMatch = service.getKabaddiMatch(matchId);
        if(kabaddiMatch!=null && score.getStatus()==MatchStatus.COMPLETED){
            score.endKabaddiMatch(kabaddiMatch);
            kabaddimatchdao.save(kabaddiMatch);
            return ResponseEntity.ok(kabaddiMatch);
        }
        else{
            throw  new IllegalArgumentException("Not completed.");
        }

    }





}
