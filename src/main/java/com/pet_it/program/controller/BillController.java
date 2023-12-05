package com.pet_it.program.controller;

import com.pet_it.program.domain.Bill;
import com.pet_it.program.domain.Customer;
import com.pet_it.program.services.billService;
import com.pet_it.program.services.customerService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    private billService billService;
    
    @Autowired
    private customerService customerService;

    @PostMapping("/generate")
    public ResponseEntity<Bill> generateBill(@RequestParam Long customerId, @RequestParam BigDecimal amount) {
        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }

        Bill bill = billService.generateBill(customer, amount);
        return ResponseEntity.ok(bill);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Bill>> getBillsByCustomer(@PathVariable Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }

        List<Bill> bills = billService.getBillsByCustomer(customer);
        return ResponseEntity.ok(bills);
    }

    @GetMapping("/total-month")
    public ResponseEntity<BigDecimal> getTotalBillsForMonth() {
        BigDecimal total = billService.getTotalBillsForMonth();
        return ResponseEntity.ok(total);
    }
}