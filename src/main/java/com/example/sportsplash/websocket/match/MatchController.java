package com.example.sportsplash.websocket.match;

import com.example.sportsplash.dao.BadmintonMatchStatusDao;
import com.example.sportsplash.dao.KabaddiMatchStateDao;
import com.example.sportsplash.dao.badmintonmatchdao;
import com.example.sportsplash.dao.kabaddimatchdao;
import com.example.sportsplash.service.sportsservice;
import com.example.sportsplash.sports.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

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

    @Autowired
    BadmintonMatchStatusDao badmintonMatchStatusDao;

    @Autowired
    KabaddiMatchStateDao kabaddiMatchStateDao;

    @MessageMapping("/updateBadmintonScore/{matchId}")
    @SendTo("/public/badmintonScoreUpdates/{matchId}")
    public ResponseEntity<Object> updateBadmintonScore(
            @DestinationVariable("matchId") int matchId,
            @RequestBody UploadBadmintonScore score){
        BadmintonMatch match = service.getBadmintonMatch(matchId);
        if(score.getUndo()) {
            List<BadmintonMatchState> state = badmintonMatchStatusDao.findLastStatusByMatchId(matchId);
            score.undoScore(match, state.get(0));
            badmintonMatchStatusDao.delete(state.get(0));
            badMintonMatchdao.save(match);
            return ResponseEntity.ok(score);
        }
        score.updateBadmintonScore(match);
        badMintonMatchdao.save(match);
        badmintonMatchStatusDao.save(score.getMatchState());
        return ResponseEntity.ok(score);
    }
    @MessageMapping("/updateKabaddiScore/{matchId}")
    @SendTo("/public/kabaddiScoreUpdates/{matchId}")
    public ResponseEntity<Object> updateKabaddiScore(
            @DestinationVariable("matchId") int matchId,
            @RequestBody UploadKabaddiScore score){
        KabaddiMatch match = service.getKabaddiMatch(matchId);

        if(score.isUndo()){
            List<KabaddiMatchState> states = kabaddiMatchStateDao.findLastStatusByMatchId(matchId);
            score.undoScore(match, states.get(0));
            kabaddiMatchStateDao.delete(states.get(0));
            kabaddimatchdao.save(match);
            return ResponseEntity.ok(score);
        }

        score.updateKabaddiScore(match);
        kabaddimatchdao.save(match);
        kabaddiMatchStateDao.save(score.getMatchState());
        return ResponseEntity.ok(score);
    }
    @MessageMapping("/startBadmintonMatch/{matchId}")
    @SendTo("/public/badmintonScoreUpdates/{matchId}")
    public ResponseEntity startBadmintonMatch(
            @DestinationVariable("matchId") int matchId,
            @RequestBody UploadBadmintonScore score) {

        BadmintonMatch badmintonMatch = service.getBadmintonMatch(matchId);
        List<BadmintonMatchState> states = badmintonMatchStatusDao.findLastStatusByMatchId(matchId);
        badmintonMatchStatusDao.deleteAll(states);

        if (badmintonMatch != null && score.getStatus() == MatchStatus.ONGOING) {
            score.startBadmintonMatch(badmintonMatch);
            badMintonMatchdao.save(badmintonMatch);
            badmintonMatchStatusDao.save(score.getMatchState());
            return ResponseEntity.ok(score);
        } else {
            throw new IllegalArgumentException("Match is not started.");
        }
    }

    @MessageMapping("/startKabaddiMatch/{matchId}")
    @SendTo("/public/kabaddiScoreUpdates/{matchId}")
    public ResponseEntity startKabaddiMatch(
            @DestinationVariable("matchId") int matchId,
            @RequestBody UploadKabaddiScore score) {

        KabaddiMatch kabaddiMatch = service.getKabaddiMatch(matchId);
        List<KabaddiMatchState> states = kabaddiMatchStateDao.findLastStatusByMatchId(matchId);
        kabaddiMatchStateDao.deleteAll(states);
        if (kabaddiMatch != null && score.getStatus() == MatchStatus.ONGOING) {
//            log.info("dfggfdgd");
            score.startKabaddiMatch(kabaddiMatch);
           kabaddimatchdao.save(kabaddiMatch);
           kabaddiMatchStateDao.save(score.getMatchState());
            return ResponseEntity.ok(score);
        } else {
            throw new IllegalArgumentException("Match is not started.");
        }
    }

    @MessageMapping("/endKabaddiMatch/{matchId}")

    @SendTo("/public/kabaddiScoreUpdates/{matchId}")
    public ResponseEntity endKabaddiMatch(@DestinationVariable("matchId") int matchId,
                                                        @RequestBody UploadKabaddiScore score){
        KabaddiMatch kabaddiMatch = service.getKabaddiMatch(matchId);
        if(kabaddiMatch!=null && score.getStatus()==MatchStatus.COMPLETED){
            score.endKabaddiMatch(kabaddiMatch);
            kabaddimatchdao.save(kabaddiMatch);
            return ResponseEntity.ok(score);
        }
        else{
            throw  new IllegalArgumentException("Not completed.");
        }

    }

}
