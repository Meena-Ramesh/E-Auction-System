package com.eas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.eas.repository"})
@SpringBootApplication

public class EAuctionSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EAuctionSystemApplication.class, args);
	}

}
