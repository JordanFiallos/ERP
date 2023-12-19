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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.sql.Date;
import java.time.LocalDateTime;
import lombok.Data;

/**
 *
 * @author Jordan
 */
@Entity
@Table(name = "visit")
@Data
public class Visit {
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
    private long id;
    
    @Column(name="concept")
    private String concept;
    
    @Column(name="observations")
    private String observations;
    
    @Column(name="scheduledDate")
    private LocalDateTime scheduledDate;

    @ManyToOne()
    @JoinColumn(name="pet_id",unique = false)
    private Pet pet;
    
    @ManyToOne()
    @JoinColumn(name="employee_id",unique = false)
    private Employee employee;
    
}
