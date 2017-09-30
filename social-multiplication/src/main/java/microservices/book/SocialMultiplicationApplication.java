package microservices.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SocialMultiplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialMultiplicationApplication.class, args);
	}

}
