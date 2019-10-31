//package com.soma.dodam.dodami.rabbitmq;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//@Component
//@Slf4j
//public class Receiver {
//
//    @Bean
//    Queue queue(){
//        return new Queue("CustomerQ",false);
//    }
//
//    @RabbitListener(queues = {"TrainQ", "SynthQ"})
//    public void processMessage(String email){
//        log.info("Recived Message {} ", email);
//    }
//}
