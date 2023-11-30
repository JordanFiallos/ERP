/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.DAO;

import com.pet_it.program.domain.employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ruben
 */

public interface usersDAO extends JpaRepository<employees,Long>{
    employees findByUsername(String username);
}
