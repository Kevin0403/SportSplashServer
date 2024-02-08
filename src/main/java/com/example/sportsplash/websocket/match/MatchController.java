package com.example.sportsplash.websocket.match;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class MatchController {

    @MessageMapping("/updateScore/{matchId}")
    @SendTo("/public/scoreUpdates/{matchId}")
    public UploadScore updateScore(
            @DestinationVariable("matchId") int matchId,
            @Payload UploadScore score
    ){
        log.info("helow");
        return score;
    }
}
