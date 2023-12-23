/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pet_it.program.DAO;

import com.pet_it.program.domain.bPurchase;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Ricard
 */
public interface bpurchaseDAO extends JpaRepository<bPurchase, Long> {
    @Query(value="SELECT * FROM b_bill WHERE dtype = \"Purchase\" AND scheduled_delivery_date = ?1 OR scheduled_delivery_date > ?1",nativeQuery=true)
    List<bPurchase> listPurchasesSinceDate(LocalDateTime fecha);
}
