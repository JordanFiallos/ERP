/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Ruben
 */
@Controller
@Slf4j
public class ControllerProgram {
    
    @GetMapping("/")
    public String index() {
        return "redirect:/calendar";
    }
    
    @GetMapping("/inicio")
    public String inicio() {
        return "inicio";
    }

    @GetMapping("/accounting")
    public String accounting() {
        return "accounting";
    }

    @GetMapping("/commercial")
    public String commercial() {
        return "commercial";
    }

    @GetMapping("/human_resources")
    public String human_resources() {
        return "human_resources";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/purchase")
    public String purchase() {
        return "purchase";
    }

    @GetMapping("/seller")
    public String seller() {
        return "seller";
    }

    @GetMapping("/veterinarian")
    public String veterinarian() {
        return "veterinarian";
    }

    @GetMapping("/Emails")
    public String Emails() {
        return "Emails";
    }
}
