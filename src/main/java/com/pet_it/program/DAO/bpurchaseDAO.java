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
 * @author Ricard & Ruben
 */
public interface bpurchaseDAO extends JpaRepository<bPurchase, Long> {
    @Query(value="SELECT * FROM b_bill WHERE dtype = \"Purchase\" AND scheduled_delivery_date = ?1 OR scheduled_delivery_date > ?1",nativeQuery=true)
    List<bPurchase> listPurchasesSinceDate(LocalDateTime fecha);
    
    @Query(value="SELECT * FROM b_bill WHERE dtype = \"Purchase\" AND semana = ?1 AND YEAR(operation_date) = ?2",nativeQuery=true)
    List<bPurchase> listPurchasesSinceSemana(int nSemana, int ano);
    
    @Query(value="SELECT * FROM b_bill WHERE dtype = \"Purchase\" AND operation_date BETWEEN ?1 AND ?2",nativeQuery=true)
    List<bPurchase> listPurchasesBetweenDates(LocalDateTime fechaInicio, LocalDateTime fechaFin);
    
    ///
    
    @Query(value="SELECT COUNT(*) FROM b_bill b WHERE dtype = \"Purchase\"",nativeQuery=true)
    int listaFacturasCompra();
    
    @Query(value="SELECT COUNT(*) FROM b_bill b WHERE dtype = \"Purchase\" AND b.semana = ?1 AND YEAR(b.operation_date) = ?2",nativeQuery=true)
    int listaComprasSemana(int semana, int año);
    
    @Query(value="SELECT SUM(b.total) FROM b_bill b WHERE dtype = \"Purchase\"",nativeQuery=true)
    int sumaTotals();
    
    @Query(value="SELECT SUM(b.total) FROM b_bill b WHERE dtype = \"Purchase\" AND b.semana = ?1 AND YEAR(b.operation_date) = ?2",nativeQuery=true)
    Integer sumaTotalsSemana(int semana, int año);
    
    @Query(value="SELECT SUM(b.quantity) FROM b_bill b WHERE dtype = \"Purchase\"",nativeQuery=true)
    int sumaCantidad();
    
    @Query(value="SELECT SUM(b.quantity) FROM b_bill b WHERE dtype = \"Purchase\" AND b.semana = ?1 AND YEAR(b.operation_date) = ?2",nativeQuery=true)
    int sumaCantidadSemana(int semana, int año);

    
    @Query("SELECT b FROM bPurchase b WHERE b.operationDate >= ?1 AND b.operationDate <= ?2 ORDER BY b.operationDate ASC")
    List<bPurchase> listaComprasRango(LocalDateTime fecha1,LocalDateTime fecha2);

    @Query("SELECT COUNT(*) FROM bPurchase b WHERE b.semana = ?1 AND YEAR(b.operationDate) = ?2")
    int contarCompras(int semana,int año);
    
    @Query("SELECT DISTINCT SUM(b.total) FROM bPurchase b WHERE b.semana = ?1 AND YEAR(b.operationDate) = ?2")
    int sumaTotalesGrafico(int semana,int año);
}

