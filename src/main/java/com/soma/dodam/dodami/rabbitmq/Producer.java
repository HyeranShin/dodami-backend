package com.soma.dodam.dodami.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendTo(String routingKye, String message) {
        logger.info("send message....");

        this.rabbitTemplate.convertAndSend(routingKye, message);
    }
}
