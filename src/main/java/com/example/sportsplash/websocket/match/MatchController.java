package com.example.sportsplash.websocket.match;

import com.example.sportsplash.dao.badmintonmatchdao;
import com.example.sportsplash.service.sportsservice;
import com.example.sportsplash.sports.BadmintonMatch;
import com.example.sportsplash.sports.MatchStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class MatchController {

    @Autowired
    private sportsservice service;

    @Autowired
    private badmintonmatchdao badMintonMatchdao;

    @MessageMapping("/updateScore/{matchId}")
    @SendTo("/public/scoreUpdates/{matchId}")
    public ResponseEntity<Object> updateScore(
            @DestinationVariable("matchId") int matchId,
            @Payload UploadScore score){
        BadmintonMatch match = service.getBadmintonMatch(matchId);
        score.updateScore(match);
        badMintonMatchdao.save(match);
        return ResponseEntity.ok(score);
    }


    @MessageMapping("/startMatch/{matchId}")
    @SendTo("/public/scoreUpdates/{matchId}")
    public ResponseEntity<Object> startMatch(
            @DestinationVariable("matchId") int matchId,
            @Payload UploadScore score){
        if(score.status == MatchStatus.ONGOING){
            BadmintonMatch match = service.getBadmintonMatch(matchId);
            score.startMatch(match);
            badMintonMatchdao.save(match);
        }

        return ResponseEntity.ok(score);
    }
}
