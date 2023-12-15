/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pet_it.program.DAO;

import com.pet_it.program.domain.TelegramAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Ricard
 */
public interface telegramAccountDAO extends JpaRepository<TelegramAccount, Long> {

}
