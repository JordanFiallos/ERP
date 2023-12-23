/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.time.LocalDateTime;
import lombok.Data;

/**
 *
 * @author Ricard
 */
public class Calendar{
    private Long id;
    private LocalDateTime scheduledDate;
    private String comentario;
    //arreglar columnas long y String
    
}