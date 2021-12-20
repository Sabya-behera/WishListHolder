package com.example.Like_and_Unlike_feature.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
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
    public Docket postApi()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Test")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.Like_and_Unlike_feature"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo()
    {
        return new ApiInfoBuilder()
                .title("Sample Documentation Using Swagger2 for our RestApi")
                .termsOfServiceUrl("")
                .license("Tomcat License")
                .licenseUrl("")
                .build();
    }
}
