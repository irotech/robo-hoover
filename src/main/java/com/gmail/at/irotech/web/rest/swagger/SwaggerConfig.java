package com.gmail.at.irotech.web.rest.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket petApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("public-robo-hoover-api")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gmail.at.irotech.web.rest.controller"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .directModelSubstitute(LocalDate.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, Arrays.asList(new ResponseMessageBuilder()
                        .code(500)
                        .message("500 Internal server error")
                        .responseModel(new ModelRef("Error"))
                        .build()))
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("ROBO HOOVER API DOCUMENTATION")
                .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut non augue leo. Curabitur pellentesque " +
                        "consequat eros id consectetur. Nunc placerat turpis maximus sapien semper, id lacinia justo vestibulum. " +
                        "Aliquam rutrum elementum luctus. Aenean ornare, dui vel convallis dapibus, tellus sem feugiat nibh, ut " +
                        "finibus ipsum turpis eget sem. Aliquam dictum risus in mollis dignissim. Donec libero ipsum, imperdiet in " +
                        "elit ut, finibus tincidunt nunc. Duis in dignissim elit. Curabitur molestie euismod tempus. Duis commodo " +
                        "ex metus, quis bibendum lorem molestie ut. Fusce ac aliquet nisl.")
                .version("1.0.0")
                .build();
    }

}
