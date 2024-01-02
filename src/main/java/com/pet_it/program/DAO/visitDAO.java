/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pet_it.program.DAO;

import com.pet_it.program.domain.Visit;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Jordan
 */
public interface visitDAO extends JpaRepository<Visit, Long>{
    
    @Query(value="SELECT * FROM visit WHERE scheduled_date = ?1 OR scheduled_date > ?1",nativeQuery=true)
    List<Visit> listVisitsSinceDate(LocalDateTime fecha);
    
}
