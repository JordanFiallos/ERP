/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.domain;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.Data;

/**
 *
 * @author Ricard
 */
@Entity
//@Table(name = "supplier")
@DiscriminatorValue("Supplier")
@Data
public class Supplier extends Person{
    @Column(name = "bureao")
    private String bureao;
    
    @Column(name = "aproach_delivery_int")
    private int aproachDeliveryInt;
    
    @Transient
    private String deliveryDateString;
}
