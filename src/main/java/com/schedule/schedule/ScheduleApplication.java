package com.schedule.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScheduleApplication {

    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(ScheduleApplication.class);


        logger.info("\ncan i see my env't variables from AWS?");
        logger.info(System.getenv("RDS_HOSTNAME"));
        logger.info(System.getenv("RDS_DB_NAME"));
        logger.info(System.getenv("RDS_PORT"));
        logger.info(System.getenv("RDS_USERNAME"));

        SpringApplication.run(ScheduleApplication.class, args);
    }

}
