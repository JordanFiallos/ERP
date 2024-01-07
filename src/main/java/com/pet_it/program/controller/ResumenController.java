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
     * @param switchView
     * checkSlide para cambiar entre graficos y lista
     * @param switchStringView
     * variable para conservar la vista
     * @param model
     * @return 
     * Vamos al metodo mostrar_lista donde usara los cambios en fecha para mostrar resumenes.
     */
    @GetMapping("/bills/costEfective")
    public String getFecha(
            @RequestParam(name="fechaInicio", required=false, defaultValue="no") String fechaInicio,
            @RequestParam(name="operacio", required=false, defaultValue="no") String operacio,
            @RequestParam(name="switchView", required=false) boolean switchView,
            @RequestParam(name="switchStringView", required=false, defaultValue="no") String switchStringView,
            Model model) {
        if (switchStringView.equals("true")) {
            switchView = true;
        }
        String fechaString = resumenService.operarWeek(fechaInicio,operacio);
        model.addAttribute("fecha",fechaString);
        model.addAttribute("tipeView",switchView);
        return mostrarLista(fechaString, switchView, model);
    }
    
    /**
     * Muestra los resumenes según String fecha.
     * @param fecha
     * @param model
     * Genera lista de Compras, Ventas y sus totales por semana indicada en la fecha.
     * @return html
     */
    private String mostrarLista(String fecha, boolean switchView, Model model){
        int numeroSemana = resumenService.extraerWeek(fecha);
        
        List<ResumenSemanal> listaResumenCompras =  resumenService.generarResumenSemanal (fecha, "Compras");
        float totalCompras = resumenService.totalSemanal(listaResumenCompras);
        float porcentajeCalculadoCompras = resumenService.totalPorcentaje(listaResumenCompras);
        
        List<ResumenSemanal> listaResumenVentas =  resumenService.generarResumenSemanal (fecha, "Ventas");
        float totalVentas = resumenService.totalSemanal(listaResumenVentas);
        float porcentajeCalculadoVentas = resumenService.totalPorcentaje(listaResumenVentas);
        
        model.addAttribute("listaResumenCompras",listaResumenCompras);
        model.addAttribute("totalCompras",totalCompras);
        model.addAttribute("listaResumenVentas",listaResumenVentas);
        model.addAttribute("totalVentas",totalVentas);
        model.addAttribute("porcentajeCalculadoCompras",porcentajeCalculadoCompras);
        model.addAttribute("porcentajeCalculadoVentas",porcentajeCalculadoVentas);
        model.addAttribute("numeroSemana",numeroSemana);
        
        if(!switchView){
            return "resumen/costEfective_list";
        } else {
            return "resumen/costEfective_graph";
        }
    }
    
    //Muestra total y cantidad de facturas por año y semana.
    //FUNCIONA PERO NO ESTA COMO LINK, (el total entre semanas para el proyecto lo realiza Ruben.)
    @GetMapping("/bills/totalPorSemana")
    public String totalPorSemana(
            @RequestParam(name="fechaInicio", required=false, defaultValue="no") String fechaInicio,
            @RequestParam(name="fechaFinal", required=false, defaultValue="no") String fechaFinal,
            Model model) {
        List<ResumenSemanal> listaResumenCompras = resumenService.generarTotalEntreFechas(fechaInicio,fechaFinal,"Compras");
        List<ResumenSemanal> listaResumenVentas = resumenService.generarTotalEntreFechas(fechaInicio,fechaFinal,"Ventas");
        model.addAttribute("listaResumenCompras",listaResumenCompras);
        model.addAttribute("listaResumenVentas",listaResumenVentas);
        return "resumen/totalPorSemana";
    }
    //
}
