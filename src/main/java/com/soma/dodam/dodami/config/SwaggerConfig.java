package com.soma.dodam.dodami.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)     //기본 반환 메세지 설정
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())   //기본 패키지 설정
                .paths(PathSelectors.ant("/api/**"))   //노출할 API 경로 패턴 설정
                .build();
    }

    //Swagger UI 페이지에 노출할 정보 커스텀
    @SuppressWarnings("deprecation")
    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "도담이(Dodami)",
                "Software Maestro 10기 도담팀 프로젝트\n- 아빠의 목소리로 태아와의 유대감을 형성하는 태교 애플리케이션",
                "1.0",
                "Terms of Service URL",
                "신혜란",
                "License",
                "License URL"
        );
        return apiInfo;
    }
}

