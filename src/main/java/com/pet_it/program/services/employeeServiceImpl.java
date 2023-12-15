/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.services;

import com.pet_it.program.DAO.employeeDAO;
import com.pet_it.program.domain.Employee;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jordan
 */
@Service
public class employeeServiceImpl implements employeeService {

    @Autowired
    private employeeDAO employeedao;

    @Override
    public List<Employee> llistarUsuaris() {
        List<Employee> employees = employeedao.findAll();
        return employees;
    }

    @Override
    public void afegirUsuari(Employee employee) {
        employeedao.save(employee);
    }
    
    @Override
    public Employee getPersonById(Long id) {
        return employeedao.findById(id).orElse(null);
    }
    
    @Override
    public Employee cercarUsuari(Employee employee) {
        return employeedao.findById(employee.getId()).orElse(null);
    }

    @Override
    public void eliminarUsuari(Long id) {
        Employee employee = getPersonById(id);
        employeedao.delete(employee);
    }
}