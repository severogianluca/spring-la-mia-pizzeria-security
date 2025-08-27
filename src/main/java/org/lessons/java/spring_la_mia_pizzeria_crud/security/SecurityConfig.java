package org.lessons.java.spring_la_mia_pizzeria_crud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    @SuppressWarnings("removal")
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests()
            .requestMatchers("/pizzas/create", "/pizzas/edit/**").hasAuthority("ADMIN")
            // tutte le richieste sul metodo post è ADMIN
            .requestMatchers(HttpMethod.POST, "/pizzas/**").hasAuthority("ADMIN")
            .requestMatchers("/ingredienti", "/ingredienti/**").hasAuthority("ADMIN")
            .requestMatchers("/pizzas", "/pizzas/**").hasAnyAuthority("USER","ADMIN")
            .requestMatchers("/**").permitAll()
            .and().formLogin()
            .and().logout()
            .and().exceptionHandling();

        return http.build();
    }

    @SuppressWarnings("deprecation")
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(null);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder(){

        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
