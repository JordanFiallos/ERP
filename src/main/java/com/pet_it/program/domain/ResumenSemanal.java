/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.domain;

import lombok.Data;

/**
 *
 * @author Ricard
 */
@Data
public class ResumenSemanal {
    private Long id;
    private String tipo;
    private int year;
    private int week;
    private Long idProducto;
    private String nombreProducto;
    private int totalCantidad;
    private float totalCoste;
    private float costeIndividual;
    private float porcentaje;
}
