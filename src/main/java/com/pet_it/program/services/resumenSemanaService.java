/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pet_it.program.services;

import com.pet_it.program.domain.ResumenSemanal;
import java.util.List;

/**
 *
 * @author Ricard
 */
public interface resumenSemanaService {
    public String consultarDataString(String fecha);
    public List<ResumenSemanal> generarResumenSemanal (String fechaInicio, String tipoFactura);
    public String operarWeek(String fecha, String opcio);
    public float totalSemanal(List<ResumenSemanal> listaResumenes);
    public float totalPorcentaje(List<ResumenSemanal> listaResumen);
    public int extraerWeek(String fechaInicio);
    public List<ResumenSemanal> generarTotalEntreFechas (String fechaInicio, String fechaFinal, String tipoFactura);
}
