/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.domain;

import java.time.LocalDateTime;
import lombok.Data;

/**
 *
 * @author Ricard
 */
@Data
public class Calendar{
    private Long id;
    private LocalDateTime scheduledDate;
    private Long employeeId;
    private String employeeName;
    
    private Long objectiveId;
    private String objectiveName;
    
    private Long causeId;
    private String causeName;
    
    private String comentario;
    
    private String rutaModificacion;
}