package com.soma.dodam.dodami.web;

import com.soma.dodam.dodami.auth.Auth;
import com.soma.dodam.dodami.rabbitmq.Receiver;
import com.soma.dodam.dodami.rabbitmq.Sender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/messagequeue")
@Slf4j
public class RabbitMQController {


    private final Sender sender;
    private final Receiver receiver;
//    @Auth
    @GetMapping("/test")
    public ResponseEntity<Void> postMessage(
            String message
//            HttpServletRequest httpServletRequest,
//                                            @PathVariable Long contentsIdx
    ) {
        sender.send(Sender.NAME.SynthQ,message);
//        while(true) {
//            receiver.processMessage();
//        }
        log.info(message);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
