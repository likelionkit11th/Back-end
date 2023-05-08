package likelion.PostSite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= DataSourceAutoConfiguration.class)
public class PostSiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostSiteApplication.class, args);
	}

}
