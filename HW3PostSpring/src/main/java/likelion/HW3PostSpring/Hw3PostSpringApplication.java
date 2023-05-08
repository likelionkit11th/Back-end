package likelion.HW3PostSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class Hw3PostSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(Hw3PostSpringApplication.class, args);
	}

}
