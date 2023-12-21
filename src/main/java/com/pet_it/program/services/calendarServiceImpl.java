/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.services;

import com.pet_it.program.domain.Calendar;
import com.pet_it.program.domain.Visit;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ricard
 */
@Service
public class calendarServiceImpl {
    @Autowired
    private visitServiceImpl visitService;
    
    public List<Calendar> listadoVisitas () {
        List<Visit> listaVisites = visitService.llistarVisites();
        for (Visit visita : listaVisites) {
            LocalDateTime fecha = visita.getScheduledDate();
            Long employeeId = visita.getEmployee().getId();
            String employeUsername = visita.getEmployee().getUsername();
            Long customerId = visita.getPet().getCustomers().getId();
            String customerName = visita.getPet().getCustomers().getFirstname();
            String comentario = "Visita programada";
        }
        return new ArrayList<>();
    }
}
