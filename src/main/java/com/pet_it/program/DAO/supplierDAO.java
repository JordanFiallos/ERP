package com.pet_it.program.DAO;

import com.pet_it.program.domain.Supplier;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author Ricard
 */
public interface supplierDAO  extends JpaRepository<Supplier, Long>{
    
    @Query(value="SELECT * , DATE_ADD(NOW(), INTERVAL aproach_delivery_int WEEK) AS deliveryDate FROM veterinarypetit.suppliers",nativeQuery=true)
    List<Supplier> findAllSuppliersAndApplyDate();
    
    @Query(value="SELECT * , DATE_ADD(NOW(), INTERVAL aproach_delivery_int WEEK) AS deliveryDate FROM veterinarypetit.suppliers WHERE id = ?1",nativeQuery=true)
    Supplier findSupplierByIdAndApplyDate(Long id);
}
