//package com.soma.dodam.dodami.rabbitmq;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class Sender {
//    private final RabbitMessagingTemplate template;
//
//    public enum NAME {
//        TrainQ,
//        SynthQ
//    }
//
//    @Bean
//    Queue queue1(){
//        return new Queue(NAME.TrainQ.name(), false);
//    }
//
//    @Bean
//    Queue queue2(){
//        return new Queue(NAME.SynthQ.name(), false);
//    }
//
//    public void send(NAME queueName, String message){
//        template.convertAndSend(queueName.name(),message);
//        log.info("Message Send {} ",message);
//    }
//}