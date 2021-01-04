package odfd.com.msscbeerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsscBeerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsscBeerServiceApplication.class, args);
	}

}
