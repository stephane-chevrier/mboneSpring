package com.example.mbonespring;

import com.example.mbonespring.models.interfaces.ExpertsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class MboneSpringApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(MboneSpringApplication.class);
	public static void main(String[] args) {

		SpringApplication.run(MboneSpringApplication.class, args);
		LOGGER.info("hello world");
	}
}
