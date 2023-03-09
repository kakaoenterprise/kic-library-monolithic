package com.kep.library.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class SwaggerUiWebMvcConfiguration implements WebMvcConfigurer {
    private final String baseUrl;

    public SwaggerUiWebMvcConfiguration(
        @Value("${springfox.documentation.swagger-ui.base-url:}") String baseUrl){
        this.baseUrl = baseUrl;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        String baseUrl = StringUtils.trimTrailingCharacter(this.baseUrl, '/');
        registry.addResourceHandler(baseUrl + "/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
                .resourceChain(false);
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController(baseUrl + "/swagger-ui/")
                .setViewName("forward:" + baseUrl + "/swagger-ui/index.html");
        registry.addRedirectViewController("/v2/swagger", "/v2/api-docs");
        registry.addRedirectViewController("/swagger/docs/v2", "/v2/api-docs");

    }


/*
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/appi/manageraccount")
                .allowedOrigins("http://editor.swagger.io");
        registry.addMapping("/v2/api-docs.*")
                .allowedOrigins("http://editor.swagger.io");
    }
*/
}
