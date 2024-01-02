/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pet_it.program.services;

import com.pet_it.program.domain.bPurchase;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Ricard
 */
public interface purchaseService {
    public bPurchase getProductoById(Long id);
    public bPurchase cercarProducto(bPurchase purchase);
    public List<bPurchase> llistarPurchasesDesdeFecha(LocalDateTime fecha);
}
