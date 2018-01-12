package com.wbkit.bigScreen;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BigScreenApplication {

	private static Logger logger = Logger.getLogger(BigScreenApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BigScreenApplication.class, args);
		logger.info("============= SpringBoot Start Success =============");
	}
}
