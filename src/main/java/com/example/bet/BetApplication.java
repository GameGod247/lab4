package com.example.bet;

import com.example.bet.model.Bet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class BetApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(BetApplication.class);
		app.setDefaultProperties(Map.of("server.port", "8083"));
		app.run(args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}


}
