/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.services;

import com.pet_it.program.DAO.roleDAO;
import com.pet_it.program.domain.Employee;
import com.pet_it.program.domain.Role;
import java.util.ArrayList;
import java.util.Collections;
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
    public Employee getAllRolesWithEmployee(Employee employee){
        Long id = employee.getId();
        List<Role> roles = roleDAO.findByIdEmployee(id);
        employee.setRols(roles);
        return employee;
    }
    
    @Override
    public Role getRoleById(Long id) {
        return roleDAO.findById(id).orElse(null);
    }
    
    @Override
    public void deleteRolesById(Long id) {
        List<Role> listaRoles = roleDAO.findByIdEmployee(id);
        for(Role rol : listaRoles){
            Long idRol = rol.getIdRol();
            roleDAO.deleteById(idRol);
        }
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
    public boolean updateRoles(Employee employee, List<String> roles) {
        boolean rolesActivos = false;
        employee = employeeservice.cercarUsuari(employee);
        if (roles != null) {
            if (!roles.isEmpty()) {
                Long id = employee.getId();
                List<Role> listaRolesEmployee = getAllRolesById(id);
                for (Role rol : listaRolesEmployee) {
                    rolesActivos = true;
                    deleteRolesById(rol.getIdRol());
                }
                
                for (String nombreRol : roles) {
                    Role nuevoRol = new Role();
                    nuevoRol.setNom(nombreRol);
                    nuevoRol.setIdEmployee(employee);
                    roleDAO.save(nuevoRol);
                }
            }
        }
        return rolesActivos;
    }
    
    private int conteoDeRoles(Long id){
        return roleDAO.countRolesByIdEmployee(id);
    }
    
    private List<String> listaRoles(){
        List<String> listaDeRoles = new ArrayList<>();
        listaDeRoles.add("ACCOUNTING");
        listaDeRoles.add("COMMERCIAL");
        listaDeRoles.add("SELLER");
        listaDeRoles.add("HUMAN");
        listaDeRoles.add("VETERINARIAN");
        listaDeRoles.add("PURCHASE");
        
        Collections.sort(listaDeRoles);
        return listaDeRoles;
    }
}
