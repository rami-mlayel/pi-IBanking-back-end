package esprit.pi.SoftIB;

import esprit.pi.SoftIB.dto.Tmm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class SoftIBApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoftIBApplication.class, args);
	}

}
