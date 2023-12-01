/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author Ruben
 */
@Configuration
public class ConfiguracioWeb implements WebMvcConfigurer {
    
    @Override
    public void addViewControllers(ViewControllerRegistry registre){
        registre.addViewController("/").setViewName("index");
        registre.addViewController("/login");
        registre.addViewController("/errors/error403").setViewName("/errors/error403");
    }
}
