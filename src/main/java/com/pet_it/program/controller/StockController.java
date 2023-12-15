/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.controller;

import com.pet_it.program.domain.Product;
import com.pet_it.program.services.productServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Jordan
 */
@Controller
@Slf4j
public class StockController {

    /* @Autowired
    private ProductServiceImpl productoservicelmpl;

    @RequestMapping(value = "/products_form", method = RequestMethod.GET)
    public String displayLogin(Model model) {
        model.addAttribute("products", new Product());
        return "products/products_form";
    }

    @PostMapping("/buy")
    public String ShowResult(Product products) {

        productoservicelmpl.afegirProducto(products);

        return "products/products_list";
    }

    @GetMapping("/products_list")
    public String ShowList(Model model) {

        model.addAttribute("products", productoservicelmpl.llistarProductos());

        return "products/products_list";
    }

    @GetMapping("/products_purchase")
    public String ShowListPurchase(Model model) {

        model.addAttribute("products", productoservicelmpl.llistarProductos());

        return "products/products_purchase";
    }

    @GetMapping("/updateProducts/{id}")
    public String Update(Product products, Model model) {
        model.addAttribute("products", productoservicelmpl.cercarProducto(products));
        return "products/products_form";
    }

    @GetMapping("/deleteProducts/{id}")
    public String Delete(Product products) {
        productoservicelmpl.eliminarProducto(products);
        return "redirect:/products_list";
    }*/
}
