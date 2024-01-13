/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.controller;

import com.pet_it.program.domain.Sells;
import com.pet_it.program.services.sellService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


/**
 *
 * @author Jordan
 */
@Controller
@Slf4j
public class SalesController {

    @Autowired
    private sellService sellService;

    @GetMapping("/sells_listCompras")
    public String ShowPurchases(Model model) {
        List<Sells> sells = sellService.getPurchases();

        model.addAttribute("sells", sells);

        return "sells/sells_listCompras";
    }

    @GetMapping("/sells_listSales")
    public String ShowSales(Model model) {

        model.addAttribute("sales", sellService.getSales());

        return "sells/sells_listSales";

    }

}
