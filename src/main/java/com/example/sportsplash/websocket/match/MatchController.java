package com.example.sportsplash.websocket.match;

import com.example.sportsplash.dao.badmintonmatchdao;
import com.example.sportsplash.dao.kabaddimatchdao;
import com.example.sportsplash.service.sportsservice;
import com.example.sportsplash.sports.BadmintonMatch;
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

@Controller
@Slf4j
@CrossOrigin("*-")
public class MatchController {

    @Autowired
    private sportsservice service;

    @Autowired
    private badmintonmatchdao badMintonMatchdao;
    @Autowired
    private kabaddimatchdao kabaddimatchdao;

    @MessageMapping("/updateBadmintonScore/{matchId}")
    @SendTo("/public/scoreUpdates/{matchId}")
    public ResponseEntity<Object> updateBadmintonScore(
            @DestinationVariable("matchId") int matchId,
            @Payload UploadScore score){
        BadmintonMatch match = service.getBadmintonMatch(matchId);
        score.updateBadmintonScore(match);
        badMintonMatchdao.save(match);
        return ResponseEntity.ok(score);
    }
    @MessageMapping("/updateKabaddiScore/{matchId}/{matchPoint}/{pointType}")
    @SendTo("/public/scoreUpdates/{matchId}")
    public ResponseEntity<Object> updateKabaddiScore(
            @PathVariable("matchId") int matchId,
            @PathVariable("matchPoint") int points,
            @PathVariable("pointType") String type,
            @Payload UploadScore score){
        KabaddiMatch match = service.getKabaddiMatch(matchId);
        score.updateKabaddiScore(match, points, type);
        kabaddimatchdao.save(match);
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
