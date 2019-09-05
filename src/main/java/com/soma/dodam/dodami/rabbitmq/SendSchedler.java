package com.soma.dodam.dodami.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

//@Component
//public class Producer {
//    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    public void sendTo(String routingKye, String message) {
//        logger.info("send message....");
//
//        this.rabbitTemplate.convertAndSend(routingKye, message);
//    }
//}

public class Producer {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        AmqpTemplate amqpTemplate = context.getBean(AmqpTemplate.class);

        amqpTemplate.convertAndSend("simplequeue", "Hello World");

    }

}



