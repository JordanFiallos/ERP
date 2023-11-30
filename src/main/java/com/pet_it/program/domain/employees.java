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
import java.sql.Date;
import lombok.Data;

/**
 *
 * @author Jordan
 */
@Data
@Entity
@Table(name = "employees")
public class employees extends Person {

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "nextAvailableWorkTime")
    private Date nextAvailableWorkTime;
}
