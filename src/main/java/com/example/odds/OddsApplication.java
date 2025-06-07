package com.example.odds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@SpringBootApplication
public class OddsApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(OddsApplication.class);
		app.setDefaultProperties(Map.of("server.port", "8082"));
		app.run(args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
