/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pet_it.program.DAO;

import com.pet_it.program.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Jordan
 */
public interface employeeDAO extends JpaRepository<Employee, Long> {

    Employee findByUsername(String username);

    @Query("SELECT COUNT(*) FROM Employee e WHERE e.username = ?1 ")
    int contarVecesUsuario(String username);
}
