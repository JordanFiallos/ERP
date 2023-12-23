/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.services;

import com.pet_it.program.DAO.bpurchaseDAO;
import com.pet_it.program.domain.bPurchase;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ricard
 */
@Service
public class purchaseServiceImpl implements purchaseService {
    
    @Autowired
    private bpurchaseDAO purchasedao;
    
    @Override
    public bPurchase getProductoById(Long id) {
        return purchasedao.findById(id).orElse(null);
    }

    @Override
    public bPurchase cercarProducto(bPurchase purchase) {
        return purchasedao.findById(purchase.getId()).orElse(null);
    }
    
    @Override
    public List<bPurchase> llistarPurchasesDesdeFecha(LocalDateTime fecha) {
        return purchasedao.listPurchasesSinceDate(fecha);
    }
}
