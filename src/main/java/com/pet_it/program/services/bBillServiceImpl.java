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
import java.time.Month;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
    public int contarComprasTotals(int semana, int año) {
        return purchaseDao.sumaTotalesGrafico(semana, año);
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
    
    @Override
    public List<bPurchase> listaTodasCompras() {
        return purchaseDao.findAll();
    }

    @Override
    public List<bSell> listaTodasVentas() {
        return sellDao.findAll();
    }

    @Override
    public List<bPurchase> listaComprasRango(LocalDateTime fecha1, LocalDateTime fecha2) {
        return purchaseDao.listaComprasRango(fecha1, fecha2);
    }

    @Override
    public int contarCompras(int semana, int año) {
        return purchaseDao.contarCompras(semana, año);
    }

    @Override
    public List<bSell> listaVentasRango(LocalDateTime fecha1, LocalDateTime fecha2) {
        return sellDao.listaVentasRango(fecha1, fecha2);
    }

    @Override
    public int contarVentas(int semana, int año) {
        return sellDao.contarVentas(semana, año);
    }

    @Override
    public int contarVentasTotals(int semana, int año) {
        return sellDao.sumaTotalesGrafico(semana, año);
    }

    public void getCompras(Model model) {
        List<bPurchase> listaCompras = listaComprasRango(LocalDateTime.of(2023, Month.JANUARY, 1, 20, 30), LocalDateTime.of(2025, Month.JANUARY, 1, 20, 30));
        List<Integer> datosCompras = new ArrayList();
        List<String> guardarDias = new ArrayList();

        for (int i = 0; i < listaCompras.size(); i++) {
            String fecha = "Semana: " + listaCompras.get(i).getSemana() + " / " + listaCompras.get(i).getScheduledDeliveryDate().getYear();
            if (!guardarDias.contains(fecha)) {
                guardarDias.add(fecha);
                int numeroCompras = contarCompras(listaCompras.get(i).getSemana(), listaCompras.get(i).getScheduledDeliveryDate().getYear());
                datosCompras.add(numeroCompras);
                if (datosCompras == null) {
                    datosCompras.add(0);
                }
            }
        }
        model.addAttribute("diasCompras", guardarDias);
        model.addAttribute("datosDias", datosCompras);
    }

    public void getVentas(Model model) {
        List<bSell> listaVentas = listaVentasRango(LocalDateTime.of(2023, Month.JANUARY, 1, 20, 30), LocalDateTime.of(2025, Month.JANUARY, 1, 20, 30));
        List<Integer> datosVentas = new ArrayList();
        List<String> guardarDiasVentas = new ArrayList();

        for (int i = 0; i < listaVentas.size(); i++) {
            String fecha = "Semana: " + listaVentas.get(i).getSemana() + " / " + listaVentas.get(i).getOperationDate().getYear();
            System.out.println("Venta: " + fecha);
            if (!guardarDiasVentas.contains(fecha)) {
                guardarDiasVentas.add(fecha);
                int numeroVentas = contarVentas(listaVentas.get(i).getSemana(), listaVentas.get(i).getOperationDate().getYear());
                datosVentas.add(numeroVentas);
                System.out.println(datosVentas);
            }
        }
        model.addAttribute("diasVentas", guardarDiasVentas);
        model.addAttribute("datosDiasVentas", datosVentas);
    }

    public void getTotalCompras(Model model) {
        List<bPurchase> listaComprasTotalesFecha = listaComprasRango(LocalDateTime.of(2023, Month.JANUARY, 1, 20, 30), LocalDateTime.of(2025, Month.JANUARY, 1, 20, 30));
        HashMap<String, Integer> datosComprasTotales = new HashMap<>();
        List<Integer> listadoCompras = new ArrayList();

        for (int i = 0; i < listaComprasTotalesFecha.size(); i++) {
            int totalCompras = contarComprasTotals(listaComprasTotalesFecha.get(i).getSemana(), listaComprasTotalesFecha.get(i).getScheduledDeliveryDate().getYear());
            String keyCompras = listaComprasTotalesFecha.get(i).getScheduledDeliveryDate().getYear() + "/" + listaComprasTotalesFecha.get(i).getSemana();
//            System.out.println(totalCompras);
            if (!datosComprasTotales.containsKey(keyCompras)) {
                datosComprasTotales.put(keyCompras, totalCompras);
                listadoCompras.add(totalCompras);
            }
        }
        model.addAttribute("totalCompras", listadoCompras);
    }

    public void getTotalVentas(Model model) {
        List<bSell> listaVentasTotalesFecha = listaVentasRango(LocalDateTime.of(2023, Month.JANUARY, 1, 20, 30), LocalDateTime.of(2025, Month.JANUARY, 1, 20, 30));
        List<Integer> listadoVentas = new ArrayList();
        LinkedHashMap<String, Integer> ventasGuardadas = new LinkedHashMap<>();

        for (int i = 0; i < listaVentasTotalesFecha.size(); i++) {
            String keyMap = listaVentasTotalesFecha.get(i).getSemana() + "/" + listaVentasTotalesFecha.get(i).getOperationDate().getYear();
            int totalVenta = contarVentasTotals(listaVentasTotalesFecha.get(i).getSemana(), listaVentasTotalesFecha.get(i).getOperationDate().getYear());
            if (!ventasGuardadas.containsKey(keyMap)) {
                ventasGuardadas.put(keyMap, totalVenta);
            } else {
                System.out.println("Ya esta: " + listaVentasTotalesFecha.get(i).getSemana() + listaVentasTotalesFecha.get(i).getOperationDate());
            }
            System.out.println("Venta: " + ventasGuardadas);
        }

        listadoVentas.addAll(ventasGuardadas.values());

        model.addAttribute("totalVentas", listadoVentas);

    }

    public void postCompras(Model model, LocalDateTime fechaInicio, LocalDateTime fechaFinal) {
        List<bPurchase> listaCompras = listaComprasRango(fechaInicio, fechaFinal);
        List<Integer> datosCompras = new ArrayList();
        List<String> guardarDias = new ArrayList();

        for (int i = 0; i < listaCompras.size(); i++) {
            String fecha = "Semana: " + listaCompras.get(i).getSemana() + " / " + listaCompras.get(i).getScheduledDeliveryDate().getYear();
            if (!guardarDias.contains(fecha)) {
                guardarDias.add(fecha);
                int numeroCompras = contarCompras(listaCompras.get(i).getSemana(), listaCompras.get(i).getScheduledDeliveryDate().getYear());
                datosCompras.add(numeroCompras);
                if (datosCompras == null) {
                    datosCompras.add(0);
                }
            }
        }
        model.addAttribute("diasCompras", guardarDias);
        model.addAttribute("datosDias", datosCompras);

    }

    public void postComprasTotales(Model model, LocalDateTime fechaInicio, LocalDateTime fechaFinal) {
        List<bPurchase> listaComprasTotalesFecha = listaComprasRango(fechaInicio, fechaFinal);
        HashMap<String, Integer> datosComprasTotales = new HashMap<>();
        List<Integer> listadoCompras = new ArrayList();

        for (int i = 0; i < listaComprasTotalesFecha.size(); i++) {
            int totalCompras = contarComprasTotals(listaComprasTotalesFecha.get(i).getSemana(), listaComprasTotalesFecha.get(i).getScheduledDeliveryDate().getYear());
            String keyCompras = listaComprasTotalesFecha.get(i).getScheduledDeliveryDate().getYear() + "/" + listaComprasTotalesFecha.get(i).getSemana();
//            System.out.println(totalCompras);
            if (!datosComprasTotales.containsKey(keyCompras)) {
                datosComprasTotales.put(keyCompras, totalCompras);
                listadoCompras.add(totalCompras);
            }
        }

//        System.out.println(listadoCompras);
        model.addAttribute("totalCompras", listadoCompras);

    }

    public void postVentas(Model model, LocalDateTime fechaInicio, LocalDateTime fechaFinal) {
        List<bSell> listaVentas = listaVentasRango(fechaInicio, fechaFinal);
        List<Integer> datosVentas = new ArrayList();
        List<String> guardarDiasVentas = new ArrayList();

        for (int i = 0; i < listaVentas.size(); i++) {
            String fecha = "Semana: " + listaVentas.get(i).getSemana() + " / " + listaVentas.get(i).getOperationDate().getYear();
            System.out.println(fecha);
            if (!guardarDiasVentas.contains(fecha)) {
                guardarDiasVentas.add(fecha);
                int numeroVentas = contarVentas(listaVentas.get(i).getSemana(), listaVentas.get(i).getOperationDate().getYear());
                datosVentas.add(numeroVentas);
            }
        }
        model.addAttribute("diasVentas", guardarDiasVentas);
        model.addAttribute("datosDiasVentas", datosVentas);

    }

    public void postVentasTotales(Model model, LocalDateTime fechaInicio, LocalDateTime fechaFinal) {
        List<bSell> listaVentasTotalesFecha = listaVentasRango(fechaInicio, fechaFinal);
        List<Integer> listadoVentas = new ArrayList();
        LinkedHashMap<String, Integer> ventasGuardadas = new LinkedHashMap<>();

        for (int i = 0; i < listaVentasTotalesFecha.size(); i++) {
            String keyMap = listaVentasTotalesFecha.get(i).getSemana() + "/" + listaVentasTotalesFecha.get(i).getOperationDate().getYear();
            int totalVenta = contarVentasTotals(listaVentasTotalesFecha.get(i).getSemana(), listaVentasTotalesFecha.get(i).getOperationDate().getYear());
            if (!ventasGuardadas.containsKey(keyMap)) {
                ventasGuardadas.put(keyMap, totalVenta);
            }
            else{
                System.out.println("Ya esta: " + listaVentasTotalesFecha.get(i).getSemana() + listaVentasTotalesFecha.get(i).getOperationDate());
            }
            System.out.println("Venta: "+ventasGuardadas);
        }
        
        listadoVentas.addAll(ventasGuardadas.values());
        
        model.addAttribute("totalVentas", listadoVentas);
    }
    
}
