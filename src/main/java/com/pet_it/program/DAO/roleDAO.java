/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pet_it.program.DAO;

import com.pet_it.program.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Ricard
 */
public interface roleDAO  extends JpaRepository<Role, Long>{
    @Query(value="SELECT count(id_rol) FROM vetitdb.rols WHERE id = ?1",nativeQuery=true)
    int countRolesWithId(Long id);
}