package com.andreigravonski.adotepet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableMethodSecurity
@SpringBootApplication
public class AdotepetApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdotepetApplication.class, args);
	}
}