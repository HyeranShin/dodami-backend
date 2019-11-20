package com.soma.dodam.dodami.web;

import com.soma.dodam.dodami.auth.Auth;
import com.soma.dodam.dodami.rabbitmq.Receiver;
import com.soma.dodam.dodami.rabbitmq.Sender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(description = "RabbitMQ REST API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/rabbitmq")
@Slf4j
public class RabbitMQController {


    private final Sender sender;
    private final Receiver receiver;

    @ApiOperation(value = "음성 합성 요청")
    @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    @Auth
    @GetMapping("/{voiceModelIdx}")
    public ResponseEntity<Void> postMessage(HttpServletRequest httpServletRequest,
                                            @PathVariable Long voiceModelIdx,
                                            @RequestBody String message) {
        sender.send(Sender.NAME.SynthQ,message);
//        while(true) {
//            receiver.processMessage();
//        }
        log.info(message);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
