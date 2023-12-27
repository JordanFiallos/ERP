/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pet_it.program.DAO;

import com.pet_it.program.domain.bSell;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Ricard
 */
public interface bsellDAO extends JpaRepository<bSell, Long> {
    @Query("SELECT COUNT(*) FROM bSell b")
    int listaFacturasVenta();
    
    @Query("SELECT COUNT(*) FROM bSell b WHERE b.semana = ?1 AND YEAR(b.operationDate) = ?2")
    int listaVentaSemana(int semana,int año);

    @Query("SELECT SUM(b.total) FROM bSell b")
    int sumaTotalsVenta();
    
    @Query("SELECT SUM(b.total) FROM bSell b WHERE b.semana = ?1 AND YEAR(b.operationDate) = ?2")
    Integer sumaTotalsSemanaVenta(int semana,int año);
    
    @Query("SELECT SUM(b.quantity) FROM bSell b")
    int sumaCantidadVenta();
    
    @Query("SELECT SUM(b.quantity) FROM bSell b WHERE b.semana = ?1 AND YEAR(b.operationDate) = ?2")
    int sumaCantidadSemanaVenta(int semana,int año);
}
