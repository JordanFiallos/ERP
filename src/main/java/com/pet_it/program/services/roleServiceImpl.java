/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.services;

import com.pet_it.program.DAO.roleDAO;
import com.pet_it.program.domain.Employee;
import com.pet_it.program.domain.Role;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
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
    public HashMap<String,String> listaRolesChecked(List<Role> roles){
        HashMap<String,String> listaUnAndChecked  = new HashMap<>();
        boolean bo = false;
        List<String> rolesDisponibles = listaRoles();
        for (String nombre : rolesDisponibles) {
            for (Role rol : roles) {
                if (nombre.equals(rol.getNom())) {
                    bo = true;
                }
            }
            
            if(!bo) {
                listaUnAndChecked.put(nombre,null);
            } else {
                listaUnAndChecked.put(nombre,"checked");
            }
            bo = false;
        }
        return listaUnAndChecked;
    }
    
    @Override
    public void updateRolesWithId(Long id,HttpServletRequest request){
        List<String> rolesDisponibles = listaRoles();
        for (String nombre : rolesDisponibles) {
            if (request.getParameter(nombre) != null){
                System.out.println("usuario: "+id+" guardar rol: "+nombre);
            }
        }
        
    }
    
    private List<String> listaRoles(){
        List<String> listaDeRoles= new ArrayList<>();
        listaDeRoles.add("ACCOUNTING");
        listaDeRoles.add("COMMERCIAL");
        listaDeRoles.add("SELLER");
        listaDeRoles.add("HUMAN");
        listaDeRoles.add("VETERINARIAN");
        listaDeRoles.add("PURCHASE");
        return listaDeRoles;
    }
}
