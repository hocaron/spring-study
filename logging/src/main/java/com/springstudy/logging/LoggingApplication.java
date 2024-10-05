package com.springstudy.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoggingApplication {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(LoggingApplication.class);
        for (int count = 1; count <= 10; count++) {
            logger.trace("trace 로깅이야!!! {}", count);
            logger.debug("debug 로깅이야!!! {}", count);
            logger.info("info 로깅이야!!! {}", count);
            logger.warn("warn 로깅이야!!! {}", count);
            logger.error("error 로깅이야!!! {}", count);
        }
    }
}
