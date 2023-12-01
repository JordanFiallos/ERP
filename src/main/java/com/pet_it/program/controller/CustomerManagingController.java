package com.pet_it.program.controller;

/**
 *
 * @author Houssam
 */
import com.pet_it.program.domain.Customer;
import com.pet_it.program.services.customerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class CustomerManagingController {

    @Autowired
    private final customerService customerService;

    @Autowired
    public CustomerManagingController(customerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customer/form")
    public String showCustomerForm(Customer customer, Model model) {
        model.addAttribute("customer", customer);
        return "customers/customers_form";
    }

    @PostMapping("/customer/form")
    public String addCustomer(@ModelAttribute Customer customer) {
        customerService.addCustomer(customer);
        return "customers/customers_list";
    }

    @GetMapping("/customer_select/{id}")
    public String getCustomerById(Model model, @PathVariable Long customerId) {
        model.addAttribute(customerService.getCustomerById(customerId));
        return "customers_info";
    }

    @GetMapping("/customer/list")
    public String CustomerList(Model model) {
        return "customers/customers_list";
    }
    
    @PostMapping("/customer/list")
    public String showCustomerList(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "customers/customers_list";
    }

    @PutMapping("/customer_update/{id}")
    public String updateCustomer(@PathVariable Long customerId, @RequestBody Customer updatedCustomer) {
        customerService.updateCustomer(customerId, updatedCustomer);
        return "customers/customers_form";
    }

    @DeleteMapping("/customer_delete/{id}")
    public String deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
        return "customers/customers_list";
    }

}
