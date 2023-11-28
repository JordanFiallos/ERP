/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pet_it.program.services;

import com.pet_it.program.domain.Supplier;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ricard
 */
public interface supplierService {
    Supplier savePerson(Supplier supplier);
    public List<Supplier> getAllPersons();
    public Supplier getPersonById(Long id);
    public Supplier findPerson(Supplier supplier);
    public void deletePerson(Long id);
    public List<String> getOpcionsDelivery();
    //public Date sumWeeksDelivery(int weeks);
}
