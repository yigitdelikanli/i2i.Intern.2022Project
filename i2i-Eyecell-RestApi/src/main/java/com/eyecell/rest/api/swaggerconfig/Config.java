package com.eyecell.rest.api.swaggerconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;


@Configuration
@EnableSwagger2
public class Config {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.eyecell.rest.api"))
                .paths(regex("/.*"))
                .build().apiInfo(apiEndPointsInfo());
    }

    private ApiInfo apiEndPointsInfo(){
        return new ApiInfoBuilder().title("i2i Rest Api Swagger")
                .description("Rest Api Documentation")
                .contact(new Contact("Efekan Aydemir", "","Aydemir081@gmail.com"))
                .license("Apache 2.0")
                .build();
    }
}
