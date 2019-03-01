package com.wbkit.bigScreen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
// @MapperScan("com.wbkit.bigScreen.dao.*.*")
public class BigScreenApplication {

	private static Logger logger = LogManager.getLogger(BigScreenApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BigScreenApplication.class, args);
		logger.info("============= SpringBoot Start Success =============");
	}
}
