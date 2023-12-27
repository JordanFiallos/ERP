/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 *
 * @author Ricard
 */
@Entity
@Table(name = "Sell")
@Data
public class bSell extends bBill {
    
    @ManyToOne
    @JoinColumn(name="id_product")
    private Product product;
    
    @ManyToOne
    @JoinColumn(name="id_visit")
    private Visit visit;
    
    @ManyToOne
    @JoinColumn(name="id_customer")
    private Customer customer;
}
