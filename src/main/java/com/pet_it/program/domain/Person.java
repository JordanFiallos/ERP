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
import lombok.Data;
/**
 *
 * @author Ricard
 */
@Entity
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "firstname")
    private String firstname;
    
    @Column(name = "lastname")
    private String lastname;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "adress")
    private String adress;
    
    /**
     * State = 1 , 2 or 3 => Operative
     * State = 0 => Blocked
    */
    @Column(name = "state", nullable = false) 
    private int state = 3;
}
