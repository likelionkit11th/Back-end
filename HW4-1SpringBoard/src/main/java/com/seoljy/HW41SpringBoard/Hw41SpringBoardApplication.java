package com.seoljy.HW41SpringBoard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Hw41SpringBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(Hw41SpringBoardApplication.class, args);
	}

}
