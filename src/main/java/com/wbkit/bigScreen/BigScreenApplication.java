package com.wbkit.bigScreen;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class BigScreenApplication {

	private static Logger logger = Logger.getLogger(BigScreenApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BigScreenApplication.class, args);
		logger.info("============= SpringBoot Start Success =============");
	}
}
