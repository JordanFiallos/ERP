/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.services;

import com.pet_it.program.DAO.bpurchaseDAO;
import com.pet_it.program.DAO.bsellDAO;
import com.pet_it.program.domain.ResumenSemanal;
import com.pet_it.program.domain.bPurchase;
import com.pet_it.program.domain.bSell;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ricard
 */
@Service
@Slf4j
public class resumenSemanalServiceImpl  implements resumenSemanaService {
    
    @Autowired
    private bpurchaseDAO purchaseDao;
    
    @Autowired
    private bsellDAO sellDao;
    
    @Override
    public String consultarDataString(String fecha) {
        LocalDateTime fechaDate = prepararFecha(fecha);
        return localTimeToString(fechaDate);
    }
    
    @Override
    public List<ResumenSemanal> generarResumenCompras (String fechaInicio) {
        int nyear = extraerYear (fechaInicio);
        int nSemana = extraerWeek(fechaInicio);
        log.info("Recibido year: "+nyear);
        log.info("Recibido semana: "+nSemana);
        List<ResumenSemanal> listaResumen = resumenSemanalCompras(nSemana, nyear);
        return listaResumen;
    }
    
    @Override
    public List<ResumenSemanal> generarResumenVentas (String fechaInicio) {
        int nyear = extraerYear (fechaInicio);
        int nSemana = extraerWeek(fechaInicio);
        log.info("Recibido year: "+nyear);
        log.info("Recibido semana: "+nSemana);
        List<ResumenSemanal> listaResumen = resumenSemanalVentas(nSemana, nyear);
        return listaResumen;
    }
    
    @Override
    public String operarWeek(String fecha, String opcio){
        int semanaAOperar = 7;
        String fechaString = consultarDataString(fecha);
        LocalDateTime fechaEnUso = prepararFecha(fechaString);
        switch (opcio) {
            case "suma":
                log.info("Resumen. operacion fecha +.");
                fechaEnUso = fechaEnUso.plusDays(semanaAOperar);
                log.info("Resumen. operacion fecha :"+fechaEnUso);
            break;
            case "resta":
                log.info("Resumen. operacion fecha -.");
                fechaEnUso = fechaEnUso.minusDays(semanaAOperar);
            break;
            default:
                log.info("Resumen. operacion fecha =.");
            break;
        }
        String nuevaFecha = localTimeToString(fechaEnUso);
        fechaString = consultarDataString(nuevaFecha);
        return fechaString;
    }
    
    @Override
    public float totalSemanal(List<ResumenSemanal> listaResumenes){
        float totalResumen = 0;
        for(ResumenSemanal resumen : listaResumenes){
            totalResumen = totalResumen + resumen.getTotalCoste();
        }
        return totalResumen;
    }
    
    private int extraerYear (String fechaInicio) {
        LocalDateTime fechaEnUso = prepararFecha(fechaInicio);
        return fechaEnUso.getYear();
    }
    
    private int extraerWeek(String fechaInicio){
        LocalDateTime fechaEnUso = prepararFecha(fechaInicio);
        //Para comenzar a contar cada semana desde el lunes.
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        return fechaEnUso.get(weekFields.weekOfYear());
    }
    
    private List<bPurchase> comprasPorSemana(int nSemana, int ano) {
        return purchaseDao.listPurchasesSinceSemana(nSemana, ano);
    }
    
    private List<bSell> ventasPorSemana(int nSemana, int ano) {
        return sellDao.listSellsSinceSemana(nSemana, ano);
    }
    
    private List<bSell> visitasPorSemana(int nSemana, int ano) {
        return sellDao.listSellsVisitasSinceSemana(nSemana, ano);
    }
    
    private List<ResumenSemanal> resumenSemanalCompras(int nSemana, int nyear){
        List<bPurchase> listaCompras = comprasPorSemana(nSemana, nyear);
        List<ResumenSemanal> listaResumen = new ArrayList<>();
        Long i = 0L;
        for (bPurchase compra : listaCompras) {
            ResumenSemanal resumen = new ResumenSemanal();
            String nameProducto = compra.getProduct().getName();
            boolean boo = false;
            for (ResumenSemanal resumenHastaAhora : listaResumen) {
                if (resumenHastaAhora.getNombreProducto().equals(nameProducto)) {
                    boo = true;
                    int oldTotalCantidad = resumenHastaAhora.getTotalCantidad();
                    float oldTotalCoste = resumenHastaAhora.getTotalCoste();

                    resumenHastaAhora.setTotalCantidad(compra.getQuantity() + oldTotalCantidad);
                    resumenHastaAhora.setTotalCoste(compra.getTotal() + oldTotalCoste);
                }
            }
            if (!boo) {
                i++;
                resumen.setId(i);
                resumen.setTipo("Compra");
                resumen.setWeek(nSemana);
                resumen.setYear(nyear);
                resumen.setIdProducto(compra.getProduct().getId());
                resumen.setNombreProducto(nameProducto);

                int cantidadBill = compra.getQuantity();
                float costeBill = compra.getTotal();
                float costeOrientativo = reducirDecimales(costeBill / cantidadBill);
                
                resumen.setTotalCantidad(cantidadBill);
                resumen.setTotalCoste(costeBill);
                resumen.setCosteIndividual(costeOrientativo);
                listaResumen.add(resumen);
            }
        }
        List<ResumenSemanal> listaResumenConPorcentajes = porcentajeResumen(listaResumen);
        return listaResumenConPorcentajes;
    }
    
    private List<ResumenSemanal> resumenSemanalVentas(int nSemana, int nyear){
        List<bSell> listaVentas = ventasPorSemana(nSemana, nyear);
        List<ResumenSemanal> listaResumen = new ArrayList<>();
        Long i = 0L;
        for (bSell venta : listaVentas) {
            ResumenSemanal resumen = new ResumenSemanal();
            String nameProducto = venta.getProduct().getName();
            boolean boo = false;
            for (ResumenSemanal resumenHastaAhora : listaResumen) {
                if (resumenHastaAhora.getNombreProducto().equals(nameProducto)) {
                    boo = true;
                    int oldTotalCantidad = resumenHastaAhora.getTotalCantidad();
                    float oldTotalCoste = resumenHastaAhora.getTotalCoste();

                    resumenHastaAhora.setTotalCantidad(venta.getQuantity() + oldTotalCantidad);
                    resumenHastaAhora.setTotalCoste(venta.getTotal() + oldTotalCoste);
                }
            }
            if (!boo) {
                i++;
                resumen.setId(i);
                resumen.setTipo("Venta");
                resumen.setWeek(nSemana);
                resumen.setYear(nyear);
                resumen.setIdProducto(venta.getProduct().getId());
                resumen.setNombreProducto(nameProducto);

                int cantidadBill = venta.getQuantity();
                float costeBill = venta.getTotal();
                float costeOrientativo = reducirDecimales(costeBill / cantidadBill);
                
                resumen.setTotalCantidad(cantidadBill);
                resumen.setTotalCoste(costeBill);
                resumen.setCosteIndividual(costeOrientativo);
                listaResumen.add(resumen);
            }
        }
        List<ResumenSemanal> listaResumenConVisitas = resumenSemanalVisitas(nSemana, nyear, listaResumen);
        List<ResumenSemanal> listaResumenConPorcentajes = porcentajeResumen(listaResumenConVisitas);
        return listaResumenConPorcentajes;
    }
    
    private List<ResumenSemanal> resumenSemanalVisitas(int nSemana, int nyear, List<ResumenSemanal> listaResumen){
        List<bSell> listaVisitas = visitasPorSemana(nSemana, nyear);
        float totalOrientativo = 0;
        int cantidadOrientativa = 0;
        if(!listaVisitas.isEmpty()){
            for(bSell visita : listaVisitas){
                totalOrientativo = totalOrientativo + visita.getTotal();
                cantidadOrientativa = cantidadOrientativa + visita.getQuantity();
            }
            float costeOrientativo = reducirDecimales(totalOrientativo / cantidadOrientativa);
            ResumenSemanal resumen = new ResumenSemanal();
            resumen.setTipo("Visita");
            resumen.setIdProducto(0L);
            resumen.setNombreProducto("Visitas");
            resumen.setTotalCantidad(cantidadOrientativa);
            resumen.setTotalCoste(totalOrientativo);
            resumen.setCosteIndividual(costeOrientativo);
            listaResumen.add(resumen);
        }
        return listaResumen;
    }
    
    private List<ResumenSemanal> porcentajeResumen(List<ResumenSemanal> listaResumen){
        float totalResumen = totalSemanal(listaResumen);
        float totalOrientativo = 0;
        for(ResumenSemanal resumen : listaResumen){
            float porcentajeOrientativo = reducirDecimales((resumen.getTotalCoste() / totalResumen) * 100);
            resumen.setPorcentaje(porcentajeOrientativo);
            totalOrientativo = totalOrientativo + porcentajeOrientativo;
        }
        if(totalOrientativo != 100 && !listaResumen.isEmpty()) {
            ResumenSemanal resumen = new ResumenSemanal();
            resumen.setTipo("aproximado");
            resumen.setIdProducto(0L);
            resumen.setNombreProducto("aproximado");
            resumen.setTotalCantidad(0);
            resumen.setTotalCoste(0);
            resumen.setPorcentaje(reducirDecimales(100 - totalOrientativo));
            listaResumen.add(resumen);
        }
        return listaResumen;
    }
    
    private LocalDateTime prepararFecha(String fecha) {
        LocalDateTime fechaActual = LocalDateTime.now().with(LocalTime.of(0, 0, 0));
        Date fechaFormatada = stringToDate(fecha);
        if (fechaFormatada != null) {
            fechaActual = fechaFormatada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atStartOfDay();
        }
        return fechaActual;
    }
    
    private float reducirDecimales(double valor){
        BigDecimal valorBig = new BigDecimal(valor);
        valorBig = valorBig.setScale(2, RoundingMode.DOWN);
        return valorBig.floatValue();
    }
    
    private String localTimeToString(LocalDateTime ldt) {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return ldt.format(dtf);
        } catch (Exception e) {
            log.info("Resumen. Convertir fecha vacio/nulo.");
            return null;
        }
    }
    
    private Date stringToDate(String dateString) {
        //log.info("Resumen. filtro fecha: "+dateString);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        formatter.setLenient(false);
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            log.info("Resumen. filtro fecha vacio/nulo, utilizando fecha del sistema.");
            return null;
        }
    }
}
