package com.example.RaceService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@SpringBootApplication
public class RaceServiceApplication {
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(RaceServiceApplication.class);
		app.setDefaultProperties(Map.of("server.port", "8081"));
		app.run(args);
	}
}
