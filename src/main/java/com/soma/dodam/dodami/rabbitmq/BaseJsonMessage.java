package com.soma.dodam.dodami.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
//
//@Component
//public class Consumer {
//    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);
//
//    @RabbitListener(queues = "${myqueue}")
//    public void handler(String message) {
//        logger.info("consumer...>" + message);
//    }
//}

public class Consumer {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        AmqpTemplate amqpTemplate = context.getBean(AmqpTemplate.class);

        System.out.println(amqpTemplate.receive("simplequeue"));

        //System.exit(1);

    }

}



