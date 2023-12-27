/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pet_it.program.services;

import com.pet_it.program.domain.Calendar;
import java.util.List;

/**
 *
 * @author Ricard
 */
public interface calendarService {
    public List<Calendar> listadoVisitas (String fecha);
    public List<Calendar> listadoPurchases (String fecha);
    public List<Calendar> caledarioGlobal(List<Calendar> listaCalendario1, List<Calendar> listaCalendario2);
}
