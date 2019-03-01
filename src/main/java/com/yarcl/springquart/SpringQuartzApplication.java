package com.yarcl.springquart;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
// @MapperScan("com.wbkit.bigScreen.dao.*.*")
public class SpringQuartzApplication {

	private static Logger logger = LogManager.getLogger(SpringQuartzApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringQuartzApplication.class, args);
		logger.info("============= SpringBoot Start Success =============");
	}
}
