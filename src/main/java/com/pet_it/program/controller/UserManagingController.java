/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.controller;

import com.pet_it.program.domain.Employee;
import com.pet_it.program.domain.Role;
import com.pet_it.program.services.employeeServiceImpl;
import com.pet_it.program.services.roleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Jordan
 */
@Controller
public class UserManagingController {

    @Autowired
    private employeeServiceImpl employeeservicelmpl;
    
    @Autowired
    private roleService roleService;

    @GetMapping("/employee_form")
    public String ShowForm(Employee employee) {
        return "employees/employee_form";
    }

    @PostMapping("/SaveEmployees")
    public String ShowResult(Employee employee) {

        employeeservicelmpl.afegirUsuari(employee);

        return "employees/employee_info";
    }

    @GetMapping("/employee_list")
    public String ListEmployees(Model model) {
        List<Employee> employees = employeeservicelmpl.llistarUsuaris();
        model.addAttribute("employees", employees);
        return "employees/employee_list";
    }

    @GetMapping("/update/{id}")
    public String Update(Employee employee, Model model) {
        employee = employeeservicelmpl.cercarUsuari(employee);
        model.addAttribute("employee", employee);
        return "employees/employee_form";
    }
    
    @GetMapping("/employee_list/delete/{id}")
    public String Delete(@PathVariable Long id) {
        roleService.deleteRolesById(id);
        employeeservicelmpl.eliminarUsuari(id);
        return "redirect:/employee_list";
    }
    
    @GetMapping("/employee/roles_update/{id}")
    public String updateRole(@PathVariable Long id, Model model){
        List<Role> roles = roleService.getAllRolesById(id);
        model.addAttribute("roles", roles);
        return "roles/roles_form";
    }
}
