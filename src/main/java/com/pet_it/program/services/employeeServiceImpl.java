/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.services;

import com.pet_it.program.DAO.employeeDAO;
import com.pet_it.program.domain.employees;
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
    public List<employees> llistarUsuaris() {
        return (List<employees>) employeedao.findAll();
    }

    @Override
    public void afegirUsuari(employees employee) {

        employeedao.save(employee);
    }

    @Override
    public employees cercarUsuari(employees employee) {

        return employeedao.findById(employee.getId()).orElse(null);
    }

    @Override
    public void eliminarUsuari(employees employee) {
        employeedao.delete(employee);

    }

}