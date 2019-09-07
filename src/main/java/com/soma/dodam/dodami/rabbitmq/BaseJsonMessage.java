//package com.soma.dodam.dodami.rabbitmq;
//
//import com.sun.xml.internal.rngom.parse.host.Base;
//import org.jboss.logging.Logger;
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.stereotype.Component;
//
//@Component
//public class BaseJsonMessage {
//
//    private static final Logger logger = Logger.getLogger(BaseJsonMessage.class);
//
//    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
//    public void onMessage(Base base) {
//        logger.info("Received < " + base.toString() + " >");
//    }
//}
//
//
