package dev.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class HotelWebApiApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(HotelWebApiApplication.class, args);
		// Runnable exec = context.getBean(InsererChambre.class);
		// exec.run();
	}

}
