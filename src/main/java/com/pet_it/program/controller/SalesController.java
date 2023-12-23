/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.controller;

import com.pet_it.program.domain.Visit;
import com.pet_it.program.domain.Product;
import com.pet_it.program.domain.Customer;
import com.pet_it.program.domain.Employee;
import com.pet_it.program.domain.Sells;
import com.pet_it.program.domain.Supplier;
import com.pet_it.program.services.customerService;
import com.pet_it.program.services.employeeService;
import com.pet_it.program.services.productServiceImpl;
import com.pet_it.program.services.sellService;
import com.pet_it.program.services.sellServiceImpl;
import java.sql.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.pet_it.program.services.productService;
import com.pet_it.program.services.supplierService;
import com.pet_it.program.services.visitService;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Jordan
 */
@Controller
@Slf4j
public class SalesController {

    @Autowired
    private sellService sellService;

    @Autowired
    private productService productservice;

    @Autowired
    private visitService visitservice;

    @Autowired
    private customerService customerservice;

    @Autowired
    private employeeService employeeservice;

    @Autowired
    private supplierService supplierservice;

    /*@GetMapping("/sells_form")
    public String ShowForm(Sells sells, Model model) {
        List<Visit> visit = visitservice.llistarVisites();
        model.addAttribute("visit", visit);
        List<Supplier> suppliers = supplierservice.getAllPersons();
        model.addAttribute("suppliers", suppliers);
        List<Product> product = productservice.llistarProductos();
        model.addAttribute("product", product);
        List<Customer> customer = customerservice.getAllCustomers();
        model.addAttribute("customer", customer);
        List<Employee> employee = employeeservice.llistarUsuaris();
        model.addAttribute("employee", employee);
        return "sells/sells_form";
    }

    @PostMapping("/SavesellForm")
    public String ShowResult(Sells sells, @RequestParam(name = "visit") Long visitId,
            @RequestParam(name = "product") Long productId,
            @RequestParam(name = "customer") Long customerId,
            @RequestParam(name = "employee") Long employeeId, Model model) {

        Visit visit = visitservice.getVisitById(visitId);
        sells.setVisit(visit);
        Product product = productservice.getProductoById(productId);
        sells.setProduct(product);
        Customer customer = customerservice.getCustomerById(customerId);
        sells.setCustomer(customer);
        Employee employees = employeeservice.getPersonById(employeeId);
        sells.setEmployee(employees);

        sellService.afegirSells(sells);
        return "redirect:/sells_listCompras";
    }

    @GetMapping("/employee_list")
    public String ListEmployees(Model model) {
        List<Employee> employees = employeeservicelmpl.llistarUsuaris();
        model.addAttribute("employees", employees);
        return "employees/employee_list";
    }

    @GetMapping("/update/{id}")
    public String Update(Employee employee, Model model) {
        employee = employeeservicelmpl.cercarUsuari(employee);
        model.addAttribute("employee", employee);
        return "employees/employee_form";
    }

    @GetMapping("/employee_list/delete/{id}")
    public String Delete(@PathVariable Long id) {
        roleService.deleteRolesById(id);
        employeeservicelmpl.eliminarUsuari(id);
        return "redirect:/employee_list";
    }*/
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
