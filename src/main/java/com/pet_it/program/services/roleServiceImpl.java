/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.services;

import com.pet_it.program.DAO.roleDAO;
import com.pet_it.program.domain.Employee;
import com.pet_it.program.domain.Role;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ricard
 */
@Service
public class roleServiceImpl implements roleService {
    
    @Autowired
    private roleDAO roleDAO;
    
    @Autowired
    private employeeService employeeservice;
    
    @Override
    public List<Role> getAllRolesById(Long id){
        Employee employee = employeeservice.getPersonById(id);
        List<Role> roles = employee.getRols();
        return roles;
    }
    
    @Override
    public Role getRoleById(Long id) {
        return roleDAO.findById(id).orElse(null);
    }
    
    @Override
    public void deleteRolesById(Long id) {
        roleDAO.deleteRolesWithId(id);
    }
    
    @Override
    public Employee listaRolesChecked(Employee employee){
        List<Role> roles = getAllRolesById(employee.getId());
        List<String> rolesDisponibles = listaRoles();
        
        for (Role rol : roles) {
            if (rolesDisponibles.contains(rol.getNom())) {
                rol.setActive(true);
                rolesDisponibles.remove(rol.getNom());
            }
        }
        
        for (String disponible : rolesDisponibles) {
            Role rol = new Role();
            rol.setNom(disponible);
            roles.add(rol);
        }
        return employee;
    }
    
    @Override
    public void updateRoles(Employee employee){
        List<Role> roles = employee.getRols();
        for (Role rol : roles) {
            if(rol.getActive()){
                rol.setId(employee);
                roleDAO.save(rol);
            }
        }
        
        
        /*List<String> rolesDisponibles = listaRoles();
        for (String nombre : rolesDisponibles) {
            if (request.getParameter(nombre) != null){
                System.out.println("usuario: "+id+" guardar rol: "+nombre);
            }
        }*/
    }
    
    private List<String> listaRoles(){
        List<String> listaDeRoles = new ArrayList<>();
        listaDeRoles.add("ACCOUNTING");
        listaDeRoles.add("COMMERCIAL");
        listaDeRoles.add("SELLER");
        listaDeRoles.add("HUMAN");
        listaDeRoles.add("VETERINARIAN");
        listaDeRoles.add("PURCHASE");
        return listaDeRoles;
    }
}
