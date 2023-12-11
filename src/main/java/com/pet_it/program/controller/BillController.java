package com.pet_it.program.controller;

import com.pet_it.program.domain.Bill;
import com.pet_it.program.domain.Customer;
import com.pet_it.program.services.billService;
import com.pet_it.program.services.customerService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
    private billService billservice;
    
    @Autowired
    private customerService customerService;

    
    @GetMapping("/generate")
    public String generateBills(Model model) {
        List<Bill> bills = billservice.getBillsForMonth(); 

        BigDecimal totalForMonth = billservice.getTotalBillsForMonth();

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
    
    @GetMapping("/bills/cost-effective")
    public String showCostEffectiveBills(Model model) {
        List<Bill> costEffectiveBills = billservice.getBillsForCostEffectiveness();
        BigDecimal totalCost = billservice.calculateTotalForCostEffectiveness();

        model.addAttribute("bills", costEffectiveBills);
        model.addAttribute("totalCost", totalCost);

        return "bills_cost-effective";
    }

    @GetMapping("/bills/operative-planning")
    public String showOperativePlanningBills(Model model) {
        List<Bill> operativePlanningBills = billservice.getBillsForOperativePlanning();
        BigDecimal totalCost = billservice.calculateTotalForOperativePlanning();

        model.addAttribute("bills", operativePlanningBills);
        model.addAttribute("totalCost", totalCost);

        return "bills_operative-planning";
    }

    @GetMapping("/bills/viability")
    public String showViabilityBills(Model model) {
        List<Bill> viabilityBills = billservice.getBillsForViability();
        BigDecimal totalCost = billservice.calculateTotalForViability();

        model.addAttribute("bills", viabilityBills);
        model.addAttribute("totalCost", totalCost);

        return "bills_viability";
    }
}