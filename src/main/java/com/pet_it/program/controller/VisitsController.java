/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.controller;

import com.pet_it.program.DAO.petDAO;
import com.pet_it.program.domain.Employee;
import com.pet_it.program.domain.Pet;
import com.pet_it.program.domain.Visit;
import com.pet_it.program.services.employeeServiceImpl;
import com.pet_it.program.services.petService;
import com.pet_it.program.services.petServiceImpl;
import com.pet_it.program.services.visitServiceImpl;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Jordan
 */
@Controller
@Slf4j
public class VisitsController {

    @Autowired
    private visitServiceImpl visitService;

    @Autowired
    private petDAO petDao;
    
    @Autowired
    private employeeServiceImpl employeeService;

    @Autowired
    private petServiceImpl petService;

    @GetMapping("/visits/visits_list")
    public String visitas(Model model) {
        List<Visit> listaVisites = visitService.llistarVisites();
        model.addAttribute("visits", listaVisites);
        return "visits/visits_list";
    }

    @GetMapping("/visits/visits_form")
    public String añadirVisita(Model model) {
        List<Employee> OpcionesEmpleados = employeeService.llistarUsuaris();
        List<Pet> OpcionesPet = petDao.llistaPetsHabilitats();
        model.addAttribute("empleats", OpcionesEmpleados);
        model.addAttribute("pets", OpcionesPet);
        return "visits/visits_form";
    }
    @PostMapping("/visits/visits_form")
    public String completarVisita(Visit visit, @RequestParam(name = "employee") Long idempleat, @RequestParam(name = "pet") Long idpet) {
        Pet mascota = petService.getPetById(idpet);
        Employee empleado = employeeService.getPersonById(idempleat);
        visit.setEmployee(empleado);
        visit.setPet(mascota);
        visitService.agregarVisita(visit);
        return "redirect:/visits/visits_list";

    }

    @GetMapping("/visits/delete/{id}")
    public String eliminarVisita(Visit visita) {
        visitService.eliminarVisita(visita);
        return "redirect:/visits/visits_list";
    }

    @GetMapping("/visits/update/{id}")
    public String actualitzarVisita(Visit visita, Model model) {
        List<Employee> OpcionesEmpleados = employeeService.llistarUsuaris();
        List<Pet> OpcionesPet = petDao.llistaPetsHabilitats();
        visita = visitService.getVisitById(visita.getId());
        model.addAttribute("empleats", OpcionesEmpleados);
        model.addAttribute("pets", OpcionesPet);
        model.addAttribute("visits", visita);
        return "visits/visits_form_1";
    }
}
