package com.pet_it.program.controller;

import com.pet_it.program.domain.Bill;
import com.pet_it.program.domain.Customer;
import com.pet_it.program.services.billService;
import com.pet_it.program.services.customerService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
<<<<<<< 35b8b504eb0bb5ea2e88554ec7b154ce424c466d
import org.springframework.ui.Model;
=======
import org.springframework.stereotype.Controller;
>>>>>>> c6327644694c8f8e49a62ef55195affb0709a2f3
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Houssam
 */

@Controller
//@RestController
@RequestMapping("/api/bills")
public class BillController {

    @Autowired
    private billService billservice;
    
    @Autowired
    private customerService customerService;

    
    @GetMapping("/generate")
    public String generateBills(Model model) {
        List<Bill> bills = billService.getBillsForMonth(); 

        BigDecimal totalForMonth = billService.getTotalBillsForMonth();

        model.addAttribute("bills", bills);
        model.addAttribute("totalForMonth", totalForMonth);

        return "bills/bills";
    }
    
    @PostMapping("/generate")
    public ResponseEntity<Bill> generateBill(@RequestParam Long customerId, @RequestParam BigDecimal amount) {
        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }

        Bill bill = billservice.generateBill(customer, amount);
        return ResponseEntity.ok(bill);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Bill>> getBillsByCustomer(@PathVariable Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }

        List<Bill> bills = billservice.getBillsByCustomer(customer);
        return ResponseEntity.ok(bills);
    }

    @GetMapping("/total-month")
    public ResponseEntity<BigDecimal> getTotalBillsForMonth() {
        BigDecimal total = billservice.getTotalBillsForMonth();
        return ResponseEntity.ok(total);
    }
}