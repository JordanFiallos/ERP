/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pet_it.program.services;

import com.pet_it.program.domain.Visit;
import java.util.List;

/**
 *
 * @author Jordan
 */
public interface visitService {
    List<Visit> llistarVisites();
    void agregarVisita(Visit visita);
    void eliminarVisita(Visit visita);
    Visit getVisitById(Long id);
}
