/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pet_it.program.DAO;

import com.pet_it.program.domain.bBill;
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

    @Query("SELECT COUNT(*) FROM bPurchase b")
    int listaFacturasCompra();

    @Query("SELECT COUNT(*) FROM bPurchase b WHERE b.semana = ?1 AND YEAR(b.scheduledDaliveryDate) = ?2")
    int listaComprasSemana(int semana, int año);

    @Query("SELECT SUM(b.total) FROM bPurchase b")
    int sumaTotals();

    @Query("SELECT SUM(b.total) FROM bPurchase b WHERE b.semana = ?1 AND YEAR(b.scheduledDaliveryDate) = ?2")
    Integer sumaTotalsSemana(int semana, int año);

    @Query("SELECT SUM(b.quantity) FROM bPurchase b")
    int sumaCantidad();

    @Query("SELECT SUM(b.quantity) FROM bPurchase b WHERE b.semana = ?1 AND YEAR(b.scheduledDaliveryDate) = ?2")
    int sumaCantidadSemana(int semana, int año);

    @Query(value = "SELECT * FROM b_bill WHERE dtype = \"Purchase\" AND scheduled_delivery_date = ?1 OR scheduled_delivery_date > ?1", nativeQuery = true)
    List<bPurchase> listPurchasesSinceDate(LocalDateTime fecha);

}
