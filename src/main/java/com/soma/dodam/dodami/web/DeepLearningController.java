package com.soma.dodam.dodami.web;

//import com.soma.dodam.dodami.rabbitmq.Sender;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "RabbitMQ REST API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mq")
public class DeepLearningController {

//    private final Sender sender;

    @GetMapping("/train")
    public void recognizeTrain() {

    }

    @GetMapping("/synthesize")
    public void recognizeSynthesize() {

    }
}
