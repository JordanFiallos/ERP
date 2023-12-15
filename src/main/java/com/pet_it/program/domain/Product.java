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
import lombok.Data;

/**
 *
 * @author Jordan
 */
@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "supplier") // ajusta el nombre de la columna
    private Supplier supplier;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @Transient
    private Integer restminim = 0;

    @Column(name = "minimQuantity")
    private int minimQuantity;

    @Column(name = "interest")
    private int interest;

    @Column(name = "product")
    private int product;

    @Column(name = "quantity")
    private int quantity;

}
