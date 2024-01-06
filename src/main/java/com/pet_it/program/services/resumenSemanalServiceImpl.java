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
import java.util.Calendar;
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
    public String operarWeek(String fecha, String opcio){
        int semanaAOperar = 7;
        String fechaString = consultarDataString(fecha);
        LocalDateTime fechaEnUso = prepararFecha(fechaString);
        switch (opcio) {
            case "suma":
                fechaEnUso = fechaEnUso.plusDays(semanaAOperar);
            break;
            case "resta":
                fechaEnUso = fechaEnUso.minusDays(semanaAOperar);
            break;
            default:
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
    
    @Override
    public float totalPorcentaje(List<ResumenSemanal> listaResumen){
        float porcentajeCalculado = 0;
        float porcentajeFaltante = 0;
        for (ResumenSemanal resumen : listaResumen) {
            porcentajeCalculado = porcentajeCalculado + resumen.getPorcentaje(); 
        }
        if(porcentajeCalculado != 0){
            porcentajeFaltante = 100 - porcentajeCalculado;
        }
        float porcentajeExacto = 100 - porcentajeFaltante;
        return porcentajeExacto;
    }
    
    @Override
    public List<ResumenSemanal> generarResumenSemanal (String fechaInicio, String tipoFactura){
        int nyear = extraerYear (fechaInicio);
        int nSemana = extraerWeek(fechaInicio);
        List<Object> objectList = new ArrayList<>();
        if(tipoFactura.equals("Compras")){
            List<bPurchase> lista = comprasPorSemana(nSemana, nyear);
            for (bPurchase purchase : lista) {
                objectList.add(purchase);
            }
        } else {
            List<bSell> lista = ventasPorSemana(nSemana, nyear);
            for (bSell venta : lista) {
                objectList.add(venta);
            }
        }
        List<ResumenSemanal> lista = listarFacturasPorSemana(nSemana, nyear, tipoFactura, objectList);
        return lista;
    }
    
    private List<ResumenSemanal> listarFacturasPorSemana(int nSemana, int nyear, String tipoFactura, List<Object> objectList){
        List<ResumenSemanal> listaResumen = new ArrayList<>();
        Long i = 0L;
        for (Object factura : objectList) {
            ResumenSemanal resumen = new ResumenSemanal();
            String nameProducto = null;
            if (factura instanceof bPurchase bCompra) {
                nameProducto = bCompra.getProduct().getName();
            } else if (factura instanceof bSell bventa) {
                nameProducto = bventa.getProduct().getName();
            }
            boolean boo = false;
            for (ResumenSemanal resumenHastaAhora : listaResumen) {
                if (resumenHastaAhora.getNombreProducto().equals(nameProducto)) {
                    boo = true;
                    int oldTotalCantidad = resumenHastaAhora.getTotalCantidad();
                    float oldTotalCoste = resumenHastaAhora.getTotalCoste();
                    if (factura instanceof bPurchase bCompra) {
                        resumenHastaAhora.setTotalCantidad(bCompra.getQuantity() + oldTotalCantidad);
                        resumenHastaAhora.setTotalCoste(bCompra.getTotal() + oldTotalCoste);
                    } else if (factura instanceof bSell bventa) {
                        resumenHastaAhora.setTotalCantidad(bventa.getQuantity() + oldTotalCantidad);
                        resumenHastaAhora.setTotalCoste(bventa.getTotal() + oldTotalCoste);
                    }
                }
            }
            if (!boo) {
                i++;
                resumen.setId(i);
                resumen.setWeek(nSemana);
                resumen.setYear(nyear);
                resumen.setNombreProducto(nameProducto);
                float costeBill = 0;
                int cantidadBill = 0;
                if (factura instanceof bPurchase bCompra) {
                    resumen.setTipo("Compra");
                    resumen.setIdProducto(bCompra.getProduct().getId());
                    cantidadBill = bCompra.getQuantity();
                    costeBill = bCompra.getTotal();
                } else if (factura instanceof bSell bventa) {
                    resumen.setTipo("Venta");
                    resumen.setIdProducto(bventa.getProduct().getId());
                    cantidadBill = bventa.getQuantity();
                    costeBill = bventa.getTotal();
                }
                float costeOrientativo = reducirDecimales(costeBill / cantidadBill);
                resumen.setTotalCantidad(cantidadBill);
                resumen.setTotalCoste(costeBill);
                resumen.setCosteIndividual(costeOrientativo);
                listaResumen.add(resumen);
            }
        }
        if(!tipoFactura.equals("Compras")){
            listaResumen = resumenSemanalVisitas(nSemana, nyear, listaResumen);
        }
        List<ResumenSemanal> listaResumenConPorcentajes = porcentajeResumen(listaResumen);
        return listaResumenConPorcentajes;
    }
    
    @Override
    public List<ResumenSemanal> generarTotalEntreFechas (String fechaInicio, String fechaFinal, String tipoFactura){
        List<Object> lista = listarfacturas (fechaInicio, fechaFinal, tipoFactura);
        List<ResumenSemanal> listaResumen = new ArrayList<>();
        for (Object factura : lista){
            String fechaDeCompra = null;
            if (factura instanceof bPurchase bCompra) {
                fechaDeCompra = localTimeToString(bCompra.getOperationDate());
            } else if (factura instanceof bSell bventa) {
                fechaDeCompra = localTimeToString(bventa.getOperationDate());
            }
            int numeroSemana = extraerWeek(fechaDeCompra);
            int numeroYear = extraerYear(fechaDeCompra);
            String claveFecha = numeroYear + "-" + numeroSemana;
            
            boolean boo = false;
            for (ResumenSemanal resumen : listaResumen) {
                if (resumen.getNombreProducto().equals(claveFecha)) {
                    if (factura instanceof bPurchase bCompra) {
                        resumen.setTotalCoste(resumen.getTotalCoste() + bCompra.getTotal());
                        resumen.setTotalCantidad(resumen.getTotalCantidad() + 1);
                    } else if (factura instanceof bSell bventa) {
                        resumen.setTotalCoste(resumen.getTotalCoste() + bventa.getTotal());
                        resumen.setTotalCantidad(resumen.getTotalCantidad() + 1);
                    }
                    boo = true;
                    break;
                }
            }
            if (!boo) {
                ResumenSemanal resumen = new ResumenSemanal();
                resumen.setNombreProducto(claveFecha);
                resumen.setWeek(numeroSemana);
                resumen.setYear(numeroYear);
                if (factura instanceof bPurchase bCompra) {
                    resumen.setTipo("Compra");
                    resumen.setTotalCoste(bCompra.getTotal());
                } else if (factura instanceof bSell bventa) {
                    resumen.setTipo("Venta");
                    resumen.setTotalCoste(bventa.getTotal());
                }
                resumen.setTotalCantidad(1);
                listaResumen.add(resumen);
            }
        }
        return listaResumen;
    }
    
    private List<Object> listarfacturas (String fechaInicio, String fechaFinal, String tipoFactura) {
        LocalDateTime fechaInicioSemana = extraerLocalDateTime(fechaInicio, true);
        LocalDateTime fechaFinalSemana = extraerLocalDateTime(fechaFinal, false);
        List<Object> objectList = new ArrayList<>();
        if(tipoFactura.equals("Compras")){
            List<bPurchase> lista = purchaseDao.listPurchasesBetweenDates(fechaInicioSemana, fechaFinalSemana);
            for (bPurchase purchase : lista) {
                objectList.add(purchase);
            }
        } else {
            List<bSell> lista = sellDao.listSellsBetweenDates(fechaInicioSemana, fechaFinalSemana);
            for (bSell venta : lista) {
                objectList.add(venta);
            }
        }
        return objectList;
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
    
    private int extraerYear (String fechaInicio) {
        LocalDateTime fechaEnUso = prepararFecha(fechaInicio);
        return fechaEnUso.getYear();
    }
    
    @Override
    public int extraerWeek(String fechaInicio){
        LocalDateTime fechaEnUso = prepararFecha(fechaInicio);
        //Para comenzar a contar cada semana desde el lunes.
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        return fechaEnUso.get(weekFields.weekOfYear());
    }
    
    private LocalDateTime extraerLocalDateTime(String fecha, boolean inicioFinal){
        int yearEnUso = extraerYear(fecha);
        int nSemanaEnUso = extraerWeek(fecha);
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.WEEK_OF_YEAR, nSemanaEnUso);
        calendar.set(Calendar.YEAR, yearEnUso);

        Date date = calendar.getTime();
        LocalDateTime localDateTime;
        if(!inicioFinal) {
            localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().with(LocalTime.MAX);
            localDateTime = localDateTime.plusDays(6);
        } else {
            localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        }
        return localDateTime;
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
        /*
        //En caso de querer mostrar una linea con el porcentaje faltante en el listado. /costEfective
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
        */
        return listaResumen;
    }
    
    private float reducirDecimales(double valor){
        BigDecimal valorBig = new BigDecimal(valor);
        valorBig = valorBig.setScale(2, RoundingMode.DOWN);
        return valorBig.floatValue();
    }
    
    private LocalDateTime prepararFecha(String fecha) {
        LocalDateTime fechaActual = LocalDateTime.now().with(LocalTime.of(0, 0, 0));
        Date fechaFormatada = stringToDate(fecha);
        if (fechaFormatada != null) {
            fechaActual = fechaFormatada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atStartOfDay();
        }
        return fechaActual;
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
