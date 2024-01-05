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
    public List<ResumenSemanal> generarResumenCompras (String fechaInicio);
    public List<ResumenSemanal> generarResumenVentas (String fechaInicio);
    public String operarWeek(String fecha, String opcio);
    public float totalSemanal(List<ResumenSemanal> listaResumenes);
}
