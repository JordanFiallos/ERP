/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.controller;

import com.pet_it.program.domain.employees;
import com.pet_it.program.services.employeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Jordan
 */
@Controller
public class UserManagingController {

    @Autowired
    private employeeServiceImpl employeeservicelmpl;

    @GetMapping("/employee_form")
    public String ShowForm(employees employe) {
        return "employees/employee_form";
    }

    @PostMapping("/SaveEmployees")
    public String ShowResult(employees employe) {

        employeeservicelmpl.afegirUsuari(employe);

        return "employees/employee_info";
    }

    @GetMapping("/employee_list")
    public String ListEmployees(Model model) {
        model.addAttribute("employees", employeeservicelmpl.llistarUsuaris());
        return "employees/employee_list";
    }

    @GetMapping("/update/{id}")
    public String Update(employees employe, Model model) {

        model.addAttribute("employee_form", employeeservicelmpl.cercarUsuari(employe));
        return "employees/employee_form";
    }

    @GetMapping("/delete/{id}")
    public String Delete(employees employe) {
        employeeservicelmpl.eliminarUsuari(employe);
        return "redirect:/employee_list";
    }
}
