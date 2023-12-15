/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pet_it.program.services;

import com.pet_it.program.domain.Employee;
import com.pet_it.program.domain.Supplier;
import java.util.List;

/**
 *
 * @author Jordan
 */
public interface employeeService {

    List<Employee> llistarUsuaris(); //Metodo para listar Personas tipo Empleados

    void afegirUsuari(Employee employee); //Metodo para añadir Personas tipo Empleados

    void eliminarUsuari(Long id); //Metodo para eliminar Personas tipo Empleados
    
    public Employee getPersonById(Long id);
    
    Employee cercarUsuari(Employee employee); //Metodo para buscar Personas tipo Empleados
}
