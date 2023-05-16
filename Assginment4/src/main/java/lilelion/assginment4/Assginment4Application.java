package lilelion.assginment4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Assginment4Application {

    public static void main(String[] args) {
        SpringApplication.run(Assginment4Application.class, args);
    }

}
