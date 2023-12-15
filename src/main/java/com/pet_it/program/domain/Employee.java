/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Jordan
 */
@Entity
@Table(name = "employee")
@Data
public class Employee extends Person {
    
    private String username;
    private String password;
    
    @Column(name = "nextAvailableWorkTime")
    private LocalDateTime nextAvailableWorkTime;
    
    @OneToMany
    @JoinColumn(name="id_usuari")
    private List<Role> rols;
}