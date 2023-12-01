/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author Ruben
 */
@Configuration
@EnableWebSecurity
public class AutenticacioConfiguracio {

    @Autowired
    private UserDetailsService userDetails;

    @Autowired
    public void autenticacio(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetails).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public SecurityFilterChain security(HttpSecurity http) throws Exception {
        
        return http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/images/**").permitAll()
                .requestMatchers("/inicio/**").permitAll()
                .requestMatchers("/accounting/**").hasAnyAuthority("ACCOUNTING")
                .requestMatchers("/commercial/**").hasAnyAuthority("COMMERCIAL")
                .requestMatchers("/customer/**").hasAnyAuthority("SELLER")
                .requestMatchers("/employee_list/**").hasAnyAuthority("HUMAN")
                .requestMatchers("/veterinarian/**").hasAnyAuthority("VETERINARIAN")
                .requestMatchers("/supplier/**").hasAnyAuthority("PURCHASE")
                .anyRequest().authenticated())
                
                .formLogin((form) -> form
                .loginPage("/login").permitAll())
                .logout((logout) -> logout.deleteCookies("JSESSIONID").logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/inicio"))
                .exceptionHandling((exception) -> exception.accessDeniedPage("/errors/error403")).build();
                
        
                
        
        
        
    }

}
