package com.kep.library.configuration;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * Rest API Version
     */
    private String version;
    /**
     * RestAPI Swagger website's title
     */
    private String title = "Library Project REST API ";

    @Bean
    public Docket apiV1(){
        version = "V1.0";
        title = title + version;

        return new Docket(DocumentationType.SWAGGER_2)
                //.groupName("")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kep.library.restApiController"))
                .paths(PathSelectors.any())
                //.paths(PathSelectors.ant("/api/v1.0/**"))
                .build()
                .pathMapping("/")
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo(title, version));
    }

//    @Bean
//    public Docket apiV2(){
//        version = "V2.0";
//        title = title + version;
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName(version)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.kep.cnp.sam.management.restApiV2Controller"))
//                .paths(PathSelectors.ant("/api/v2.0/**"))
//                .build()
//                .pathMapping("/api/v2.0/")
//                .useDefaultResponseMessages(false)
//                .apiInfo(apiInfo(title, version));
//    }

    private ApiInfo apiInfo(String title, String version){
        return new ApiInfo(
                title,
                "library management system API for create, update, delete, search books",
                version,
                "service",
                new Contact("Contact Me", "www.base.com", "base@example.com"),
                "license of API"
                ,"www.base.com",
                Collections.emptyList()

        );
    }
}
