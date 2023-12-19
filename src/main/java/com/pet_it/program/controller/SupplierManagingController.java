/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.controller;

import com.pet_it.program.domain.Supplier;
import com.pet_it.program.services.supplierService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 
 * @author Ricard
 */
@Controller
public class SupplierManagingController {
    
    @Autowired
    private supplierService supplierService;
    
    @GetMapping("/supplier")
    public String showList(Model model) {
        List<Supplier> suppliers = supplierService.getAllPersons();
        model.addAttribute("suppliers", suppliers);
        return "suppliers/suppliers_list";
    }
    
    @GetMapping("/supplier/form")
    public String showForm(Supplier supplier, Model model) {
        List<String> options = supplierService.getOpcionsDelivery();
        model.addAttribute("options",options);
        model.addAttribute("defect_value", 1);
        return "suppliers/suppliers_form";
    }
    
    @PostMapping("/supplier/form")
    public String submitFormSupplier(Supplier supplier, Model model) {
        supplierService.savePerson(supplier);
        //return "suppliers/suppliers_info";
        return "redirect:/supplier";
    }
    
    @GetMapping("/supplier_update/{id}")
    public String showInfoFormUpdate(Supplier supplier, Model model){
        supplier = supplierService.findPerson(supplier);
        List<String> options = supplierService.getOpcionsDelivery();
        model.addAttribute("options",options);
        model.addAttribute("supplier",supplier);
        return "suppliers/suppliers_form";
    }
    
    @GetMapping("/supplier/delete/{id}")
    public String deletePerson(@PathVariable Long id, Model model){
        supplierService.deletePerson(id);
        return "redirect:/supplier";
    }
}
