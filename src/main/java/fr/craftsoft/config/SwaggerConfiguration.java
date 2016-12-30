package fr.craftsoft.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Date;

/**
 * Created by ayr on 30/12/16.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    public static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";
    private static final String CONTACT_NAME = "CRAFTSOFT";
    private static final String CONTACT_URL = "";
    private static final String CONTACT_EMAIL = "rekik.aymen@gmail.com";
    private static final String TITLE = "Angular springBoot API";
    private static final String DESCRIPTION = "Angular springBoot API documentation";
    private static final String VERSION = "1.0.0";
    private static final String TERSM_SERVER_URL = "";
    private static final String LICENSE = "";
    private static final String LICENSE_URL = "";
    private final Logger log = LoggerFactory.getLogger(SwaggerConfiguration.class);

    /**
     * Swagger Springfox configuration.
     *
     * @return the Swagger Springfox configuration
     */
    @Bean
    public Docket swaggerSpringfoxDocket() {
        log.debug("Starting Swagger");
        StopWatch watch = new StopWatch();
        watch.start();
        Contact contact = new Contact(
                CONTACT_NAME,
                CONTACT_URL,
                CONTACT_EMAIL);

        ApiInfo apiInfo = new ApiInfo(
                TITLE,
                DESCRIPTION,
                VERSION,
                TERSM_SERVER_URL,
                contact,
                LICENSE,
                LICENSE_URL);

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .forCodeGeneration(true)
                .genericModelSubstitutes(ResponseEntity.class)
                .ignoredParameterTypes(java.sql.Date.class)
                .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
                .directModelSubstitute(java.time.LocalDateTime.class, Date.class)
                .select()
                .paths(regex(DEFAULT_INCLUDE_PATTERN))
                .build();
        watch.stop();
        log.debug("Started Swagger in {} ms", watch.getTotalTimeMillis());
        return docket;
    }
}
