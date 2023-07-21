package io.getarrays.start_up.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("io.getarrays"))
                .build()
                .apiInfo(metaData());

    }
    private ApiInfo metaData(){
        return new ApiInfo(
                "Start_up loyiha Java Spring project",
                "FrontEnd hamda App dasturchi (Tog'oyev Muhammad) uchun",
                "Maxsus ishlab chiqilgan",
                null,
                new Contact("MilliyUz dasturi",
                        "https://github.com/sirojbekjon/start_up",
                        "sirojiddin1712@gmail.com"
                        ),
                null,
                null,
                Collections.emptyList());
    }
}


//URL FOR SWAGGER HTML
//http://localhost:8080/swagger-ui.html#/
