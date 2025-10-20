package com.andreigravonski.adotepet;

import com.andreigravonski.adotepet.service.ONGService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import com.andreigravonski.adotepet.service.ONGService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@EnableMethodSecurity
@SpringBootApplication
public class AdotepetApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdotepetApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ONGService ongService) {
		return args -> {
			// Chama nosso método para criar o admin
			ongService.criarAdminUserSeNaoExistir();
		};
	}
}