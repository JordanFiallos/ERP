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
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author Houssam
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
                .requestMatchers("/api/bills/**").hasAnyAuthority("ACCOUNTING")
                .requestMatchers("/bills/**").hasAnyAuthority("ACCOUNTING")
                .requestMatchers("/commercial/**").hasAnyAuthority("COMMERCIAL")
                .requestMatchers("/customer/list/**").hasAnyAuthority("SELLER")
                .requestMatchers("/employee_list/**").hasAnyAuthority("HUMAN")
                .requestMatchers("/pet/pet-inicio/**").hasAnyAuthority("VETERINARIAN")
                .requestMatchers("/visits/visits_form/**").hasAnyAuthority("VETERINARIAN")
                .requestMatchers("/supplier/**").hasAnyAuthority("PURCHASE")
                .requestMatchers("/login/**").permitAll()
                .anyRequest().authenticated())
                
                .formLogin((form) -> form
                .loginPage("/login").failureHandler(auth_FailureHandler()).successHandler(auth_SuccessHandler()).permitAll())
                .logout((logout) -> logout.deleteCookies("JSESSIONID").logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/inicio"))
                
                .exceptionHandling((exception) -> exception.accessDeniedPage("/errors/error403")).build();

        
    }
    @Bean
    public AuthenticationFailureHandler auth_FailureHandler(){
        return new AuthFailureHandler();
    }
    
    @Bean
    public AuthenticationSuccessHandler auth_SuccessHandler(){
        return new AuthSuccessHandler();
    }
        

}
