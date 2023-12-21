/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.controller;

import com.pet_it.program.services.calendarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Ricard
 */
@Controller
public class CalendarController {
    @Autowired
    private calendarServiceImpl calendarServiceImpl;
    
    @GetMapping("/calendar")
    public String showList(Model model) {
        
        return "";
    }
    
    @PostMapping("/calendar")
    public String showList(@PathVariable int tiempo, Model model) {
        return "";
    }
}
