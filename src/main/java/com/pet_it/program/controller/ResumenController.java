/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.controller;

import com.pet_it.program.domain.ResumenSemanal;
import com.pet_it.program.services.resumenSemanaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller para mostrar las facturas de compras y ventas por semana.
 * @author Ricard
 */
@Controller
public class ResumenController {
    @Autowired
    private resumenSemanaService resumenService;
    
    /**
     * Trabaja la fecha indicada o ninguna si esta vacia.
     * @param fechaInicio
     * String en que indicamos una fecha inicial, puede estar vacio.
     * @param operacio
     * String en que indicamos si debe realizarse una operacion, puede estar vacio.
     * @param model
     * @return 
     * Vamos al metodo mostrar_lista donde usara los cambios en fecha para mostrar resumenes.
     */
    @GetMapping("/costEfective")
    public String getFecha(
            @RequestParam(name="fechaInicio", required=false, defaultValue="no") String fechaInicio,
            @RequestParam(name="operacio", required=false, defaultValue="no") String operacio,
            Model model) {
        String fechaString = resumenService.operarWeek(fechaInicio,operacio);
        model.addAttribute("fecha",fechaString);
        return mostrarLista(fechaString, model);
    }
    
    /**
     * Muestra los resumenes según String fecha.
     * @param fecha
     * @param model
     * Genera lista de Compras, Ventas y sus totales por semana indicada en la fecha.
     * @return html
     */
    private String mostrarLista(String fecha, Model model){
        List<ResumenSemanal> listaResumenCompras = resumenService.generarResumenCompras(fecha);
        float totalCompras = resumenService.totalSemanal(listaResumenCompras);
        
        List<ResumenSemanal> listaResumenVentas = resumenService.generarResumenVentas(fecha);
        float totalVentas = resumenService.totalSemanal(listaResumenVentas);
        
        model.addAttribute("listaResumenCompras",listaResumenCompras);
        model.addAttribute("totalCompras",totalCompras);
        model.addAttribute("listaResumenVentas",listaResumenVentas);
        model.addAttribute("totalVentas",totalVentas);
        return "resumen/costEfective_list";
    }
    
    /*
    //old
    @GetMapping("/costEfective")
    public String getFecha(
            @RequestParam(name="fechaInicio", required=false, defaultValue="no") String fechaInicio,
            @RequestParam(name="fechaFinal", required=false, defaultValue="no") String fechaFinal,
            Model model) {
        List<ResumenGrafico> listaResumenes = resumenService.generarResumenCompras (fechaInicio, fechaFinal);
        model.addAttribute("listaResumenes",listaResumenes);
        model.addAttribute("totalCompras",totalCompras);
        return "";
    }
    */
}
