/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.controller;

import com.pet_it.program.DAO.petDAO;
import com.pet_it.program.domain.Customer;
import com.pet_it.program.domain.Pet;
import com.pet_it.program.services.customerServiceImpl;
import com.pet_it.program.services.petServiceImpl;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Ruben
 */
@Controller
@Slf4j
public class ControllerProgram {

    @Autowired
    private petServiceImpl petService;
    
    @Autowired
    private petDAO petDao;
    
    @Autowired
    private customerServiceImpl customerService;

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

    /*
    @GetMapping("/")
    public String index(){
        return "index";
    }
     */
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

    @GetMapping("/pet/pet-inicio")
    public String veterinarian(Model model) {
        List<Pet> pets = petDao.llistaPetsHabilitats();
        model.addAttribute("pets", pets);
        return "pet/pet-inicio";
    }

    @GetMapping("/pet/pet_form")
    public String formulariPets(Model model) {
        List<Customer> ListaCustomers = customerService.getAllCustomers();
        model.addAttribute("customers", ListaCustomers);
        return "pet/pet_form";
    }

    @PostMapping("/pet/pet_form")
    public String formulariPets2(Pet pets) {
        pets.setEstat("true");
        petService.afegirPets(pets);
        return "redirect:/pet/pet-inicio";
    }

    @GetMapping("/delete/{id}")
    public String eliminarPet(Pet pets) {
        pets = petService.getPetById(pets.getId());
        pets.setEstat("false");
        petDao.save(pets);
        return "redirect:/pet/pet-inicio";
    }

    @GetMapping("/pet/update/{id}")
    public String updatePet(Pet pets, Model model) {
        List<Customer> ListaCustomers = customerService.getAllCustomers();
        model.addAttribute("customers", ListaCustomers);
        pets = petService.buscarPets(pets);
        model.addAttribute("pets", pets);
        return "pet/pet_form2";
    }

}
