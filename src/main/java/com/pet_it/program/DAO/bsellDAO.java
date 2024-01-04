/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pet_it.program.DAO;

import com.pet_it.program.domain.bSell;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Ricard & Ruben
 */
public interface bsellDAO extends JpaRepository<bSell, Long> {
    @Query(value="SELECT * FROM b_bill WHERE dtype = \"Sell\" AND id_visit IS NULL AND semana = ?1 AND YEAR(operation_date) = ?2",nativeQuery=true)
    List<bSell> listSellsSinceSemana(int fechaSemana, int ano);
    
    @Query(value="SELECT * FROM b_bill WHERE dtype = \"Sell\" AND id_visit IS NOT NULL AND semana = ?1 AND YEAR(operation_date) = ?2",nativeQuery=true)
    List<bSell> listSellsVisitasSinceSemana(int fechaSemana, int ano);
    
    @Query(value="SELECT * FROM b_bill WHERE dtype = \"Sell\" AND operation_date BETWEEN ?1 AND ?2",nativeQuery=true)
    List<bSell> listSellsBetweenDates(LocalDateTime fechaInicio, LocalDateTime fechaFin);
    
    ///
    
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
