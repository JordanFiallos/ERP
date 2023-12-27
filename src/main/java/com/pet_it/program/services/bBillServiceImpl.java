/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.services;

import com.pet_it.program.DAO.bBillDAO;
import com.pet_it.program.DAO.bpurchaseDAO;
import com.pet_it.program.DAO.bsellDAO;
import com.pet_it.program.domain.bBill;
import com.pet_it.program.domain.bPurchase;
import com.pet_it.program.domain.bSell;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ruben
 */
@Service
@Slf4j
public class bBillServiceImpl implements bBillService {

    @Autowired
    private bBillDAO billDao;

    @Autowired
    private bpurchaseDAO purchaseDao;
    
    @Autowired 
    private bsellDAO sellDao;
    
//Compras
    @Override
    public List<bBill> getAllbBills() {
        return billDao.findAll();
    }

    @Override
    public int getAllPurchases() {
        return purchaseDao.listaFacturasCompra();
    }

    @Override
    public int comprasPorSemana(int semana, int año) {
        return purchaseDao.listaComprasSemana(semana, año);
    }

    @Override
    public List<bPurchase> getAllbPurchases() {
        return purchaseDao.findAll();
    }

    @Override
    public int sumaTotals() {
        return purchaseDao.sumaTotals();
    }

    @Override
    public Integer sumaTotalsSemana(int semana, int año) {
        return purchaseDao.sumaTotalsSemana(semana, año);
    }

    @Override
    public int sumaCantidades() {
        return purchaseDao.sumaCantidad();
    }

    @Override
    public int sumaCantidadesSemana(int semana, int año) {
        return purchaseDao.sumaCantidadSemana(semana, año);
    }

    public int semanaActual() {
        LocalDateTime fechaActual = LocalDateTime.now();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int weekNumber = fechaActual.get(weekFields.weekOfYear());
        return weekNumber;
    }

    //Ventas
    @Override
    public List<bSell> getAllbSells() {
        return sellDao.findAll();
    }
    
    @Override
    public int listaVentas() {
        return sellDao.listaFacturasVenta();
    }

    @Override
    public int ventasPorSemana(int semana, int año) {
        return sellDao.listaVentaSemana(semana, año);
    }

    @Override
    public int ventasTotals() {
        return sellDao.sumaTotalsVenta();
    }

    @Override
    public Integer ventasTotalsSemana(int semana, int año) {
        return sellDao.sumaTotalsSemanaVenta(semana, año);
    }

    @Override
    public int ventasCantidades() {
        return sellDao.sumaCantidadVenta();
    }

    
    
    
}
