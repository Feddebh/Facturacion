package edu.coderhouse.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
  

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("edu.coderhouse.jpa.controller"))
        .paths(PathSelectors.any())
        .build()
            .apiInfo(apiInfo());
  }
  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
            .title("Billing API")
            .description("This API provides functionality to manage an E-commerce sells.")
            .version("1.0.0")
            .contact(new Contact(
                    "Bohle, Luis Federico / LinkedIn:",
                    "https://www.linkedin.com/in/federico-bohle/",
                    null))
            .build();
  }
}
