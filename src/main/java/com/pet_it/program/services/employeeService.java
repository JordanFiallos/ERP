/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pet_it.program.services;

import com.pet_it.program.domain.Employee;
import java.util.List;

/**
 *
 * @author Jordan & Ricard
 */
public interface employeeService {
    
    public List<Employee> llistarUsuaris(); //Metodo para listar Personas tipo Empleados
    
    public void afegirUsuari(Employee employee); //Metodo para añadir Personas tipo Empleados
    
    public void actualizarUsuari(Employee employee);
    
    public void eliminarUsuari(Long id); //Metodo para eliminar Personas tipo Empleados
    
    public Employee getPersonById(Long id);
    
    public Employee cercarUsuari(Employee employee); //Metodo para buscar Personas tipo Empleados
    
    public int getIntents(Employee employee);
    
    public void actualitzaUsuariIntents(Employee employee);
    
    public void bloqueaPerson(Employee employee,boolean rolesActivo);

    public void desbloquejarEmpleat(Long id,Employee empleat);

}
