/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.domain;

import jakarta.persistence.Entity;
import lombok.Data;

/**
 *
 * @author Ricard
 */
@Entity
@Data
public class TelegramAccount extends Customer {
    private Long idTelegram;
    private String firstname;
    private String username;
    private String languagecode;
}
