package fr.craftsoft;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by ayr on 30/12/16.
 */
@SpringBootApplication
public class ExceptionPocApplication {

    private static final Logger log = LoggerFactory.getLogger(ExceptionPocApplication.class);

    public static void main(String[] args) {
        log.info("Initializing of application ...");
        SpringApplication.run(ExceptionPocApplication.class, args);
    }
}
