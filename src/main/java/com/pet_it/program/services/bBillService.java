/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.services;

import com.pet_it.program.domain.bBill;
import com.pet_it.program.domain.bPurchase;
import com.pet_it.program.domain.bSell;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Ruben
 */

public interface bBillService {
    
    public List<bBill> getAllbBills();
    
    public List<bPurchase> getAllbPurchases();
        
    public int getAllPurchases();
    
    public int comprasPorSemana(int semana,int año);
    
    public int sumaTotals();
    
    public Integer sumaTotalsSemana(int semana,int año);
    
    public int sumaCantidades();
    
    public int sumaCantidadesSemana(int semana,int año);
    
    public List<bSell> getAllbSells();
    
    public int listaVentas();
    
    public int ventasPorSemana(int semana,int año);
    
    public int ventasTotals();
    
    public Integer ventasTotalsSemana(int semana,int año);
    
    public int ventasCantidades();
    
    public List<bPurchase> listaTodasCompras();
    
    public List<bSell> listaTodasVentas();
    
    public List<bPurchase> listaComprasRango(LocalDateTime fecha1,LocalDateTime fecha2);
    
    public int contarCompras(int semana,int año);
    
    public List<bSell> listaVentasRango(LocalDateTime fecha1,LocalDateTime fecha2);
    
    public int contarVentas(int semana,int año);
    
    public int contarVentasTotals(int semana,int año);
    
    public int contarComprasTotals(int semana,int año);
    
    
}
