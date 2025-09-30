package com.andreigravonski.adotepet.config;

import com.andreigravonski.adotepet.service.OngUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private OngUserDetailsService ongUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF agora está ATIVADO por padrão (removemos a linha .disable())
                .authorizeHttpRequests(authorize -> authorize
                        // Regra 1: Permite acesso a recursos estáticos.
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()

                        // Regra 2: Permite acesso PÚBLICO via GET a estas URLs.
                        .requestMatchers(HttpMethod.GET, "/", "/home", "/denuncias/denunciar", "/denuncias/denuncia-sucesso", "/registro").permitAll()

                        // Regra 3: Permite acesso PÚBLICO via POST APENAS a esta URL.
                        .requestMatchers(HttpMethod.POST, "/denuncias/denunciar/salvar", "/registro").permitAll()

                        // Regra 4: Qualquer outra requisição, de qualquer tipo, exige autenticação.
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login").permitAll()
                        .defaultSuccessUrl("/ongs/listar", true)
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout").permitAll()
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(ongUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}