package com.soma.dodam.dodami;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

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

}
