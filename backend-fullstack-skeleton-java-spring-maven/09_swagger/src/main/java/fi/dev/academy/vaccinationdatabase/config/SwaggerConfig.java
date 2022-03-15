package fi.dev.academy.vaccinationdatabase.config;

import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;



// Generate documentation for a Spring REST API http://www.baeldung.com/swagger-2-documentation-for-spring-rest-api
// Use only springfox-boot-starter https://mvnrepository.com/artifact/io.springfox/springfox-boot-starter
// not 'springfox-swagger2' with 'springfox-swagger-ui'
@Configuration
public class SwaggerConfig {

    // OPENS at http://localhost:8080/swagger-ui/ & http://localhost:8080/v2/api-docs?group=backbone-api

    @Bean
        // https://dzone.com/articles/how-to-automatically-document-api-endpoints-via-sw
    ApiInfo apiInfo() {
        final ApiInfoBuilder builder = new ApiInfoBuilder();
        builder.title("Mika's skeleton template for FullStack applications").version("0.1").license("CC BY 4.0 Le Gall 2022")
                .description("Human friendly list of all backend endpoints used in API");
        return builder.build();
    }

    // https://www.tutorialspoint.com/spring_boot/spring_boot_enabling_swagger2.htm
    @Bean
    public Docket swaggerSpringMvcPlugin() {
        // https://springfox.github.io/springfox/docs/current/#quick-start-guides
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).useDefaultResponseMessages(false)
                .groupName("backbone-api")
                .select()
                .paths(PathSelectors.any())
//            .paths(Predicates.not(PathSelectors.regex("/error"))) // Exclude Spring error controllers
                // https://springfox.github.io/springfox/docs/current/#configuring-springfox
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).build()
                .directModelSubstitute(LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(LocalDateTime.class, java.util.Date.class)
                .directModelSubstitute(ZonedDateTime.class, java.util.Date.class)
                .produces(Sets.newHashSet("application/json"));
//            .tags(new Tag(" ", " "), tags());
    }

//    @Bean
//    Tag[] tags() {
//        return new Tag[]{
//                new Tag("Orders", "Verbs for orders"),
//                new Tag("Vaccinations", "Verbs for vaccinations")
//        };
//    }
}