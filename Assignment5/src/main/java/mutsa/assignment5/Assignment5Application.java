package mutsa.assignment5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Assignment5Application {

    public static void main(String[] args) {
        SpringApplication.run(Assignment5Application.class, args);
    }

}
