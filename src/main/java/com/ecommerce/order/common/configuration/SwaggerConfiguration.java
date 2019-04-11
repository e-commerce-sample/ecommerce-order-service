package com.ecommerce.order.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static java.util.Collections.emptyList;
import static springfox.documentation.builders.PathSelectors.any;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
@EnableSwagger2
@Profile(value = {"local", "dev"})
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(SWAGGER_2)
                .select()
                .apis(basePackage("com.ecommerce.order"))
                .paths(any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Ecommerce order API",
                "Ecommerce order API",
                "1.0",
                null,
                null,
                null,
                null,
                emptyList());
    }

}
