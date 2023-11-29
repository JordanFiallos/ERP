/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Set;
import lombok.Data;

/**
 *
 * @author Jordan
 */
@Entity
@Table(name = "users")
@Data
public class Person {

    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
    @Column(name = "id_user")
    private long idUsuari;
    
    @Column(name="username")
    private String username;
    
    @Column(name = "password")
    private String password;
    
  
    @OneToMany 
    @JoinColumn(name="id_usuari") 
    private List<Role> rols;
}
