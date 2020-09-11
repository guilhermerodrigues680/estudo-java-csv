package com.example.javacsv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavacsvApplication {

	private static Logger LOG = LoggerFactory.getLogger(JavacsvApplication.class);

	public static void main(String[] args) {
		LOG.info("STARTING THE APPLICATION");
		SpringApplication.run(JavacsvApplication.class, args);
		LOG.info("APPLICATION FINISHED");
	}

}
