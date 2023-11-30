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

    @GetMapping("/customers/form")
    public String showCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/customers_form";
    }

    @PostMapping("/customers/form")
    public String addCustomer(@ModelAttribute Customer customer) {
        customerService.addCustomer(customer);
        return "redirect:/customers_list";
    }

    @GetMapping("/list")
    public String showCustomerList(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "customer_list";
    }

    @GetMapping("/{id}")
    public String showCustomerInfo(@PathVariable Long id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        return "customer_info";
    }

    @GetMapping("/")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable Long customerId) {
        return customerService.getCustomerById(customerId);
    }

    @PostMapping("/{customerId}")
    public Customer addCustomerr(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    @PutMapping("/{customerId}")
    public Customer updateCustomer(@PathVariable Long customerId, @RequestBody Customer updatedCustomer) {
        return customerService.updateCustomer(customerId, updatedCustomer);
    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
    }


}
