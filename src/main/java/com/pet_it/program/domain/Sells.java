/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.sql.Date;
import java.time.LocalDate;
import lombok.Data;

/**
 *
 * @author Jordan
 */
@Entity
@Table(name = "sells")
@Data
public class Sells {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "visit", nullable = true)
    private Visit visit;

    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "customer", nullable = true)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "employee", nullable = false)
    private Employee employee;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "operation_date")
    private LocalDate operationDate;

    @Transient
    private String month;

    @Column(name = "quantity_sold")
    private int quantitySold;

}
