/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.controller;

import com.pet_it.program.domain.Employee;
import com.pet_it.program.domain.Product;
import com.pet_it.program.domain.Supplier;
import com.pet_it.program.services.employeeService;
import com.pet_it.program.services.productServiceImpl;
import com.pet_it.program.services.supplierService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Jordan
 */
@Controller
@Slf4j
public class ProductManagingController {

    @Autowired
    private productServiceImpl productoservicelmpl;

    @Autowired
    private supplierService suplierservices;

    @RequestMapping(value = "/products_form", method = RequestMethod.GET)
    public String displayProductForm(Model model) {
        List<Supplier> suppliers = suplierservices.getAllPersons();
        model.addAttribute("suppliers", suppliers);
        model.addAttribute("product", new Product());
        return "products/products_form";
    }

    @PostMapping("/SaveProducts")
    public String ShowResult(Product product, @RequestParam(name = "supplier") Long supplierId, Model model) {
        Supplier supplier = suplierservices.getPersonById(supplierId);
        product.setSupplier(supplier);

        productoservicelmpl.afegirProducto(product);

        return "redirect:/products_list";
    }

    @GetMapping("/products_list")
    public String ShowList(Model model) {
        model.addAttribute("products", productoservicelmpl.llistarProductos());
        return "products/products_list";
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
    }

    @PostMapping("/SaveProductsEffectivePurchase")
    public String ShowResult2(Product products, Model model, @RequestParam(required = false, name = "quantitySumar") int quantitySumar) {
        String resultMessage = productoservicelmpl.afegirProducto2(products,quantitySumar);
        model.addAttribute("products", resultMessage);
        return "redirect:/products_list";
    }

    @GetMapping("/products_purchase")
    public String ShowListPurchase(Model model) {
        model.addAttribute("products", productoservicelmpl.llistarProductos());
        
        return "products/products_purchase";
    }

    @GetMapping("/updateProducts2/{id}")
    public String Update2(Product products, Model model) {
        model.addAttribute("products", productoservicelmpl.cercarProducto(products));
        return "products/products_form2";
    }

}
