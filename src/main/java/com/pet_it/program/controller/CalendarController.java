/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.controller;

import com.pet_it.program.domain.Calendar;
import com.pet_it.program.services.calendarService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Ricard
 */
@Controller
public class CalendarController {
    @Autowired
    private calendarService calendarService;
    
    @GetMapping("/calendar")
    public String showList(@RequestParam(name="fecha", required=false, defaultValue="no") String fecha, Model model) {
        List<Calendar> programacionDeVisitas = calendarService.listadoVisitas(fecha);
        List<Calendar> programacionDeRecepcionProductos = calendarService.listadoPurchases(fecha);
        List<Calendar> programacionGlobal = calendarService.caledarioGlobal(programacionDeVisitas, programacionDeRecepcionProductos);
        
        model.addAttribute("programacionGlobal",programacionGlobal);
        return "calendar/calendar_list";
    }
}
