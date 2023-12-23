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
 * @author Jordan & Ricard
 */
@Service
public class employeeServiceImpl implements employeeService {

    @Autowired
    private employeeDAO employeedao;

    @Autowired
    private encriptadorService encriptadorService;

    @Override
    public List<Employee> llistarUsuaris() {
        List<Employee> employees = employeedao.findAll();
        return employees;
    }

    @Override
    public void afegirUsuari(Employee employee) {
        String pass = employee.getPassword();
        employee.setPassword(encriptadorService.encriptaPassw(pass));
        employeedao.save(employee);
    }
    
    @Override
    public void actualizarUsuari(Employee employee){
        employee = cercarUsuari(employee);
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

    @Override
    public int getIntents(Employee employee) {
        return employee.getState();
    }

    @Override
    public void actualitzaUsuariIntents(Employee employee) {
        employee.setState(employee.getState() - 1);
        employeedao.save(employee);
    }

    @Override
    public void bloqueaPerson(Employee employee, boolean rolesActivo) {
        employee = cercarUsuari(employee);
        if (!rolesActivo) {
            employee.setState(0);
            employeedao.save(employee);
        }
    }

    @Override
    public void desbloquejarEmpleat(Long id, Employee empleat) {
        empleat = getPersonById(id);
        empleat.setState(3);
        employeedao.save(empleat);
    }
}
