/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.services;

import com.pet_it.program.DAO.supplierDAO;
import com.pet_it.program.domain.Supplier;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ricard
 */
@Service
public class supplierServiceImpl implements supplierService {
    
    @Autowired
    private supplierDAO supplierDAO;
    
    @Override
    public Supplier savePerson(Supplier supplier) {
        return supplierDAO.save(supplier);
    }

    @Override
    public List<Supplier> getAllPersons() {
        List<Supplier> suppliers = showDatetoString(supplierDAO.findAll());
        return suppliers;
    }

    @Override
    public Supplier getPersonById(Long id) {
        return supplierDAO.findById(id).orElse(null);
    }

    @Override
    public Supplier findPerson(Supplier supplier) {
        return supplierDAO.findById(supplier.getId()).orElse(null);
    }

    @Override
    @Transactional
    public void deletePerson(Long id) {
        Supplier supplier = supplierDAO.findById(id).orElse(null);
        supplierDAO.delete(supplier);
    }
    
    /**
     * Debuelve una lista de numeros desde el min a max para el select de 
     * semanas para la recepcion de un envio
     * @return List<String>
     */
    @Override
    public List<String> getOpcionsDelivery(){
        int min = 1;
        int max = 12;
        List<String> options = new ArrayList<>();
        for (int i = min; i <= max ; i++) {
            String opcionPorEscrito = i+"";
            options.add(opcionPorEscrito);
        }
        return options;
    }
    
    /**showDatetoString
     * Guardo como valor String la fecha en el atributo deliveryDateString de 
     * Supplier para mostrarlo llamando el objeto
     * @param listaSuppliers
     * @return List<Supplier>
     */
    private List<Supplier> showDatetoString(List<Supplier> listaSuppliers){
        for(Supplier supplier : listaSuppliers){
            Long id = supplier.getId();
            java.sql.Timestamp sqlDate = supplierDAO.findByIdASupplierDate(id);
            
            java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String stringDate = dateFormat.format(utilDate);
            
            supplier.setDeliveryDateString(stringDate);
        }
        return listaSuppliers;
    }
}
