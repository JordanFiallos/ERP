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
 * @author Ricard
 */
@Data
@Entity
@Table(name="rols")
public class Role {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long idRol;
    
    @Column(name = "nom")
    private String nom;
    
    @ManyToOne
    @JoinColumn(name="id_usuari", nullable = false)
    private Employee idEmployee;
    
    @Transient
    private boolean active;
    
    public String getNom(){
        return this.nom;
    }
    
    public boolean getActive(){
        return this.active;
    }
}
