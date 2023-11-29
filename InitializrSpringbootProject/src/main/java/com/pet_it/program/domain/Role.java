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
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author Jordan
 */
@Data
@Entity
@Table(name="rols")
public class Role implements Serializable{
    
    private static final long serialVersionUID=1L;

    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
    private long idRol;
    
    @Column(name = "nom")
    private String nom;
}

