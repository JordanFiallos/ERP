/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pet_it.program.services;

import com.pet_it.program.domain.Employee;
import com.pet_it.program.domain.Role;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 * @author Ricard
 */
public interface roleService {
    public List<Role> getAllRolesById(Long id);
    public Role getRoleById(Long id);
    public void deleteRolesById(Long id);
    public Employee listaRolesChecked(Employee employee);
    public void updateRoles(Employee employee, List<String> roles);
}
