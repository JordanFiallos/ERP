/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author Ruben
 */
@Configuration
@EnableWebSecurity
public class configurationSecurity {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("Ruben").password(encoder().encode("123")).roles("USER").build());
        manager.createUser(User.withUsername("Ricard").password(encoder().encode("345")).roles("EMPLOYEE").build());
        manager.createUser(User.withUsername("Jordan").password(encoder().encode("678")).roles("ADMIN").build());
        manager.createUser(User.withUsername("Houssam").password(encoder().encode("910")).roles("USER").build());
        return manager;

    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        String[] staticResources = {
            "/images/**"};

        http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/").permitAll().requestMatchers(staticResources).permitAll()
                .requestMatchers(("/user/**")).hasAnyRole("USER", "ADMIN", "EMPLOYEE")
                .requestMatchers(("/employee/**")).hasAnyRole("ADMIN", "EMPLOYEE")
                .requestMatchers(("/admin/**")).hasRole("ADMIN")
                .anyRequest().authenticated())
                .formLogin((form -> form.loginPage("/login").permitAll()));
        return http.build();

    }
//    @Bean
//    public AuthenticationEntryPoint autentificacion(){
//        BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
//        
//        return entryPoint;
//        
//    }

}
