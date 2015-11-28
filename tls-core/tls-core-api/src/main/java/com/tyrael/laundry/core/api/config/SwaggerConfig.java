package com.tyrael.laundry.core.api.config;

import com.google.common.base.Predicate;

import org.joda.time.DateTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * 
 * @author Mark Martinez, created Nov 29, 2015
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .directModelSubstitute(DateTime.class, String.class)
                .groupName("tls")
                .select()
                .paths(paths()) // and by paths
                .build()
                .apiInfo(apiInfo());
    }

    //Select any api that matches one of these paths
    @SuppressWarnings("unchecked")
    private Predicate<String> paths() {
        return or(
                regex("/user.*"),
                regex("/brand.*"),
                regex("/branch.*"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "PMC REST API",
                "PMC REST API Documentation.",
                "1.0",
                "API TOS",
                "pmc@tyraelsoft.com",
                "", //license label
                "" //license url
        );
    }

}
