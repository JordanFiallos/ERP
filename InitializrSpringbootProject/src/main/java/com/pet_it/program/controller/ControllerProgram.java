/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.controller;

import com.pet_it.program.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Ruben
 */
@Controller
@Slf4j
public class ControllerProgram {
    
    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }
    
    @GetMapping("/user")
    public String user(){
        return "user";
    }
    
    @GetMapping("/employee")
    public String employee(){
        return "employee";
    }
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    
    
}