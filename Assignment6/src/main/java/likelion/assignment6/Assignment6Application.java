package likelion.assignment6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Assignment6Application {

    public static void main(String[] args) {
        SpringApplication.run(Assignment6Application.class, args);
    }

}
