/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.services;

import com.pet_it.program.DAO.roleDAO;
import com.pet_it.program.domain.Employee;
import com.pet_it.program.domain.Role;
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
    
    @Override
    public List<Role> getAllRolesOfEmployee(Employee employee){
        Long id = employee.getId();
        System.out.println("atencion "+ id);
        //int numeroRoles = roleDAO.countRolesWithId(id);
        List<Role> roles = roleDAO.findListOfRolesWithId(id);
        return roles;
    }
    
    @Override
    public Role getRoleById(Long id) {
        return roleDAO.findById(id).orElse(null);
    }
}
