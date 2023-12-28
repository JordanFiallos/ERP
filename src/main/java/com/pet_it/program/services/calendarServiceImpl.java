/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.services;

import com.pet_it.program.domain.Calendar;
import com.pet_it.program.domain.CalendarComparator;
import com.pet_it.program.domain.Visit;
import com.pet_it.program.domain.bPurchase;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ricard
 */
@Service
@Slf4j
public class calendarServiceImpl implements calendarService {
    @Autowired
    private visitService visitService;
    
    @Autowired
    private purchaseService purchaseService;
    
    @Override
    public List<Calendar> listadoVisitas (String fecha) {
        List<Calendar> listaCalendario = new ArrayList<>();
        LocalDateTime fechaActual = prepararFecha(fecha);
        
        List<Visit> listaVisites = visitService.llistarVisitesDesdeFecha(fechaActual);
        Long i = 0L;
        
        for (Visit visita : listaVisites) {
            Calendar visitsCalendar = new Calendar();
            
            i++;
            visitsCalendar.setId(i);
            visitsCalendar.setTipo("Visita");
            visitsCalendar.setScheduledDate(visita.getScheduledDate());
            visitsCalendar.setScheduledDateString(formatDateTimeLocal(visita.getScheduledDate()));
            visitsCalendar.setEmployeeId(visita.getEmployee().getId());
            visitsCalendar.setEmployeeName(visita.getEmployee().getUsername());
            
            visitsCalendar.setObjectiveId(visita.getPet().getId());
            visitsCalendar.setObjectiveName(visita.getPet().getName());
            
            visitsCalendar.setCauseId(visita.getPet().getCustomers().getId());
            visitsCalendar.setCauseName(visita.getPet().getCustomers().getFirstname());
            
            //String comentario = "Visita programada";
            visitsCalendar.setComentario(visita.getObservations());
            
            visitsCalendar.setRutaModificacion("/visits/update/"+visita.getId());
            
            listaCalendario.add(visitsCalendar);
        }
        return listaCalendario;
    }
    
    @Override
    public List<Calendar> listadoPurchases (String fecha) {
        List<Calendar> listaCalendario = new ArrayList<>();
        LocalDateTime fechaActual = prepararFecha(fecha);
        
        List<bPurchase> listaCompras = purchaseService.llistarPurchasesDesdeFecha(fechaActual);
        Long i = 0L;
        
        for (bPurchase compra : listaCompras) {
            Calendar visitsCalendar = new Calendar();
            
            i++;
            visitsCalendar.setId(i);
            visitsCalendar.setTipo("Compra");
            visitsCalendar.setScheduledDate(compra.getScheduledDeliveryDate());
            visitsCalendar.setScheduledDateString(formatDateTimeLocal(compra.getScheduledDeliveryDate()));
            visitsCalendar.setEmployeeId(compra.getEmployee().getId());
            visitsCalendar.setEmployeeName(compra.getEmployee().getUsername());
            
            visitsCalendar.setObjectiveId(compra.getProduct().getId());
            visitsCalendar.setObjectiveName(compra.getProduct().getName() +" x"+ compra.getQuantity());
            
            visitsCalendar.setCauseId(compra.getSupplier().getId());
            visitsCalendar.setCauseName(compra.getSupplier().getFirstname());
            
            visitsCalendar.setComentario(compra.getSupplier().getBureao());
            
            visitsCalendar.setRutaModificacion("0");
            listaCalendario.add(visitsCalendar);
        }
        return listaCalendario;
    }
    
    @Override
    public List<Calendar> caledarioGlobal(List<Calendar> listaCalendario1, List<Calendar> listaCalendario2) {
        List<Calendar> listaUnida = new ArrayList<>();
        listaUnida.addAll(listaCalendario1);
        listaUnida.addAll(listaCalendario2);
        Collections.sort(listaUnida, new CalendarComparator());
        
        return listaUnida;
    }
    
    private LocalDateTime prepararFecha(String fecha) {
        LocalDateTime fechaActual = LocalDateTime.now().with(LocalTime.of(0, 0, 0));
        Date fechaFormatada = stringToDate(fecha);
        if (fechaFormatada != null) {
            fechaActual = fechaFormatada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atStartOfDay();
        }
        return fechaActual;
    }
    
    private Date stringToDate(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        formatter.setLenient(false);
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            log.info("Calendario. filtro fecha vacio/nulo, utilizando fecha del sistema.");
            return null;
        }
    }
    
    public static String formatDateTimeLocal(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm");
        return dateTime.format(formatter);
    }
}
