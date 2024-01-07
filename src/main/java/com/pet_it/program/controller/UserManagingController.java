/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.controller;

import com.pet_it.program.DAO.employeeDAO;
import com.pet_it.program.domain.Employee;
import com.pet_it.program.services.employeeService;
import com.pet_it.program.services.roleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Jordan & Ricard
 */
@Controller
public class UserManagingController {

    @Autowired
    private employeeService employeeService;
    
    @Autowired
    private roleService roleService;
    
    private String mensajeError = null;

    @GetMapping("/employee_form")
    public String ShowForm(Employee employee) {
        return "employees/employee_form";
    }
    
    @PostMapping("/SaveEmployees")
    public String ShowResult(Employee employee) {   
        employee = roleService.getAllRolesWithEmployee(employee);
        boolean comprovaUsuari = employeeService.afegirUsuari(employee);
        if(comprovaUsuari == false){
            mensajeError = "El usuario ya existe";
        }
        return "redirect:/employee_list";
    }
    
    @GetMapping("/employee_list")
    public String ListEmployees(Model model) {
        if(mensajeError != null){
            model.addAttribute("errorMessage",mensajeError);
            mensajeError = null;
        }
        List<Employee> employees = employeeService.llistarUsuaris();
        model.addAttribute("employees", employees);
        return "employees/employee_list";
    }
    
    @GetMapping("/update/{id}")
    public String Update(Employee employee, Model model) {
        employee = employeeService.cercarUsuari(employee);
        model.addAttribute("employee", employee);
        return "employees/employee_form_1";
    }
    
    @PostMapping("/employee_form_1")
    public String actualizarUsuario(Employee employee){
        employee = roleService.getAllRolesWithEmployee(employee);
        boolean comprovaActualitzacio = employeeService.actualizarUsuari(employee);
        if(comprovaActualitzacio == false){
            mensajeError = "El nombre de usuario escrito al actualizar ya existe";
        }
        return "redirect:/employee_list";
    }
    
    
    @GetMapping("/employee_list/delete/{id}")
    public String Delete(@PathVariable Long id) {
        roleService.deleteRolesById(id);
        employeeService.eliminarUsuari(id);
        return "redirect:/employee_list";
    }
    
    @GetMapping("/employee/roles_form/{id}")
    public String formRole(@PathVariable Long id, Model model){
        Employee employee = employeeService.getPersonById(id);
        Employee employeeWithRolesList = roleService.listaRolesChecked(employee);
        model.addAttribute("employeeUserName", employee.getUsername());
        model.addAttribute("employee", employeeWithRolesList);
        return "roles/roles_form";
    }
    
    @PostMapping("/employee/roles_update")
    public String updateRole(Employee employee, Model model, @RequestParam(required = false, name = "roles") List<String> roles){
        boolean rolesActivos = roleService.updateRoles(employee,roles);
        employeeService.bloqueaPerson(employee, rolesActivos);
        return "redirect:/employee_list";
    }
    
    @GetMapping("/desbloqueja/{id}")
    public String desbloquejarEmpleat(@PathVariable Long id,Employee empleat){
        employeeService.desbloquejarEmpleat(id, empleat);
        return "redirect:/employee_list";
    }
}