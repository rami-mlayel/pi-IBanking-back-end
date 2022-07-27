package esprit.pi.SoftIB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableRetry
public class SoftIBApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoftIBApplication.class, args);
	}

}
