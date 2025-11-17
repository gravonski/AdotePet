package com.andreigravonski.adotepet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import com.andreigravonski.adotepet.service.ONGService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@EnableMethodSecurity
@SpringBootApplication
public class AdotepetApplication {

	@Value("${spring.datasource.url}")
	private String dbUrl;

	public static void main(String[] args) {
		SpringApplication.run(AdotepetApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ONGService ongService) {
		return args -> {
			System.out.println("==================================================");
			System.out.println("INICIANDO DIAGNÓSTICO DE DEPLOY");
			System.out.println("Valor lido de ${spring.datasource.url}: " + dbUrl);
			System.out.println("==================================================");
			ongService.criarAdminUserSeNaoExistir();
		};
	}

}