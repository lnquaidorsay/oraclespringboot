package com.id.OracleSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class OracleSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(OracleSpringBootApplication.class, args);
	}

	/*
	 * Au démarrage Spring va executer cette méthode et le placer dans son contexte
	 * Ainsi il pourra être injecter avec Autowired
	 */
	@Bean
	public BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}
//	https://stackoverflow.com/questions/66514100/spring-data-jpa-problem-to-get-list-of-object-manytomany-relationship
}
