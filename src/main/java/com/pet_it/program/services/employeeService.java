/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pet_it.program.services;

import com.pet_it.program.domain.employees;
import java.util.List;

/**
 *
 * @author Jordan
 */
public interface employeeService {

    List<employees> llistarUsuaris(); //Metodo para listar Personas tipo Empleados

    void afegirUsuari(employees employee); //Metodo para añadir Personas tipo Empleados

    void eliminarUsuari(employees employee); //Metodo para eliminar Personas tipo Empleados

    employees cercarUsuari(employees employee); //Metodo para buscar Personas tipo Empleados
}
