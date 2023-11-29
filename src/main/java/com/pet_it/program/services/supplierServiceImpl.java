/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.services;

import com.pet_it.program.DAO.supplierDAO;
import com.pet_it.program.domain.Supplier;
import java.sql.Date;
import java.time.LocalDate;

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
        List<Supplier> listaSuppliers = supplierDAO.findAllSuppliersAndApplyDate();
        applyDateAll(listaSuppliers);
        
        return supplierDAO.findAll();
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
    
    private void applyDateAll(List<Supplier> listaSuppliers){
        for(Supplier supplier : listaSuppliers){
            Date deliveryDate = supplier.getDeliveryDate();
            //System.out.println("Atencion al listado fechas: "+ supplier.toString());
            //LocalDate localDate1 = deliveryDate.toLocalDate();
        }
    }
}
