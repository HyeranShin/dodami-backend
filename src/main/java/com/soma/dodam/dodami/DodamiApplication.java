package com.soma.dodam.dodami;

//import com.soma.dodam.dodami.rabbitmq.Producer;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.Scheduled;

@EnableJpaAuditing  //JPA Auditing 활성화
@SpringBootApplication
public class DodamiApplication {

    public static final String APPLICATION_LOCATIONS = "spring.config.location="
            +"classpath:application.yml, "
            +"classpath:aws.yml";

    public static void main(String[] args) {
        new SpringApplicationBuilder(DodamiApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
    }

//    @Value("${myqueue}")
//    String queue;
//
//    @Bean
//    Queue queue() {
//        return new Queue(queue, false);
//    }
//
//    @Autowired
//    Producer producer;
//
//    @Bean
//    CommandLineRunner sender(Producer producer) {
//        return args -> {
//            producer.sendTo(queue, "Hello !!!");
//        };
//    }
//
//    @Scheduled(fixedDelay = 500L)
//    public void sendScheduleMessage() {
//        producer.sendTo(queue, "Message Delevery : " + new Date());
//    }
}
