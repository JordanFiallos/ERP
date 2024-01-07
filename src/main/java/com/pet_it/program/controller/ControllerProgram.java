/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.controller;

import com.pet_it.program.services.bBillServiceImpl;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Ruben
 */
@Controller
@Slf4j
public class ControllerProgram {

    @Autowired
    private bBillServiceImpl billService;
    
    
    @GetMapping("/")
    public String index() {
        return "redirect:/calendar";
    }
    
    @GetMapping("/inicio")
    public String inicio() {
        return "inicio";
    }

    @GetMapping("/accounting")
    public String accounting() {
        return "accounting";
    }

    @GetMapping("/commercial")
    public String commercial() {
        return "commercial";
    }

    @GetMapping("/human_resources")
    public String human_resources() {
        return "human_resources";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/purchase")
    public String purchase() {
        return "purchase";
    }

    @GetMapping("/seller")
    public String seller() {
        return "seller";
    }

    @GetMapping("/veterinarian")
    public String veterinarian() {
        return "veterinarian";
    }

    @GetMapping("/Emails")
    public String Emails() {
        return "Emails";
    }
   

    @GetMapping("/comparacion")
    public String comparacion(Model model) {
        //Purchases
        model.addAttribute("bills", billService.getAllbPurchases());
        model.addAttribute("purchases", billService.getAllPurchases());
        model.addAttribute("total", billService.sumaTotals());
        model.addAttribute("quantities", billService.sumaCantidades());
        model.addAttribute("purchaseWeek", billService.comprasPorSemana(billService.semanaActual(), LocalDate.now().getYear()));
        model.addAttribute("totalWeek", billService.sumaTotalsSemana(billService.semanaActual(), LocalDate.now().getYear()));
        //Sells

        model.addAttribute("sells", billService.getAllbSells());
        model.addAttribute("sellsTotal", billService.listaVentas());
        model.addAttribute("sellsTotalWeek", billService.ventasPorSemana(billService.semanaActual(), LocalDate.now().getYear()));
        model.addAttribute("totalSells", billService.ventasTotals());
        model.addAttribute("totalSellsWeek", billService.ventasTotalsSemana(billService.semanaActual(), LocalDate.now().getYear()));
        model.addAttribute("sellQuantities", billService.ventasCantidades());
        return "comparacion";
    }

    @PostMapping("/comparacion")
    public String comparacionBusqueda(@RequestParam(name = "semana") int semana, @RequestParam(name = "anyo") int año, Model model) {
        //Purchases
        model.addAttribute("bills", billService.getAllbPurchases());
        model.addAttribute("purchases", billService.getAllPurchases());
        model.addAttribute("total", billService.sumaTotals());
        model.addAttribute("quantities", billService.sumaCantidades());
        model.addAttribute("purchaseWeek", billService.comprasPorSemana(semana, año));
        model.addAttribute("totalWeek", billService.sumaTotalsSemana(semana, año));
        //Sells
        model.addAttribute("sells", billService.getAllbSells());
        model.addAttribute("sellsTotal", billService.listaVentas());
        model.addAttribute("sellsTotalWeek", billService.ventasPorSemana(semana, año));
        model.addAttribute("totalSells", billService.ventasTotals());
        model.addAttribute("totalSellsWeek", billService.ventasTotalsSemana(semana, año));
        model.addAttribute("sellQuantities", billService.ventasCantidades());
        return "comparacion";
    }
    
     @GetMapping("/grafico")
    public String grafico(Model model) {
        List<List<?>> listaGetCompras = billService.getCompras();
        model.addAttribute("diasCompras",listaGetCompras.get(0));
        model.addAttribute("datosDias",listaGetCompras.get(1));
        
        List<List<?>> listaGetTotalCompras = billService.getTotalCompras();
        model.addAttribute("totalCompras",listaGetTotalCompras.get(0));
        
        List<List<?>> listaGetVentas = billService.getVentas();
        model.addAttribute("diasVentas",listaGetVentas.get(1));
        model.addAttribute("datosDiasVentas",listaGetVentas.get(0));
        
        List<List<?>> listaGetTotalVentas = billService.getTotalVentas();
        model.addAttribute("totalVentas",listaGetTotalVentas.get(0));
        
        return "grafico";
    }

    @PostMapping("/grafico")
    public String graficoPost(Model model, @RequestParam(name = "inicio") LocalDateTime fechaInicio, @RequestParam(name = "final") LocalDateTime fechaFinal) {
        List<List<?>> listaGetCompras = billService.postCompras(fechaInicio, fechaFinal);
        model.addAttribute("diasCompras",listaGetCompras.get(0));
        model.addAttribute("datosDias",listaGetCompras.get(1));
        
        List<List<?>> listaGetTotalCompras = billService.postComprasTotales(fechaInicio, fechaFinal);
        model.addAttribute("totalCompras",listaGetTotalCompras.get(0));
        
        List<List<?>> listaGetVentas = billService.postVentas(fechaInicio, fechaFinal);
        model.addAttribute("diasVentas",listaGetVentas.get(1));
        model.addAttribute("datosDiasVentas",listaGetVentas.get(0));
        
        List<List<?>> listaGetTotalVentas = billService.postVentasTotales(fechaInicio, fechaFinal);
        model.addAttribute("totalVentas",listaGetTotalVentas.get(0));
        return "grafico";
    }
    
}
