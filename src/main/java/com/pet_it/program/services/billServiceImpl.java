/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.services;

import com.pet_it.program.DAO.billDAO;
import com.pet_it.program.domain.Bill;
import com.pet_it.program.domain.Customer;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Houssam
 */
@Service
public class billServiceImpl implements billService {

    @Autowired
    private billDAO billRepository;

    @Override
    public Bill generateBill(Customer customer, BigDecimal amount) {
        Bill bill = new Bill();
        bill.setCustomer(customer);
        bill.setAmount(amount);
        bill.setIssueDate(LocalDate.now());
        return billRepository.save(bill);
    }

    @Override
    public List<Bill> getBillsByCustomer(Customer customer) {
        return billRepository.findByCustomer(customer);
    }

    @Override
    public BigDecimal getTotalBillsForMonth() {
        LocalDate now = LocalDate.now();
        LocalDate startOfMonth = now.withDayOfMonth(1);
        LocalDate endOfMonth = now.with(TemporalAdjusters.lastDayOfMonth());

        List<Bill> bills = billRepository.findByIssueDateBetween(startOfMonth, endOfMonth);
        return bills.stream().map(Bill::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Bill> getBillsForMonth() {
        // Assuming your Bill entity has a date field and you want bills for the current month
        LocalDate startOfMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate endOfMonth = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());

        return billRepository.findByIssueDateBetween(startOfMonth, endOfMonth);
    }

    @Override
    public List<Bill> getBillsForCostEffectiveness() {
        // Implement logic to retrieve bills for cost-effectiveness
        // For example, you might want to fetch bills with specific criteria.
        // Replace the following with your actual logic:
        return billRepository.findByCostEffectivenessCategory("your_cost_effectiveness_category");
    }

    @Override
    public BigDecimal calculateTotalForCostEffectiveness() {
        // Implement logic to calculate total for cost-effectiveness
        // For example, you might want to sum the amounts of relevant bills.
        // Replace the following with your actual logic:
        List<Bill> costEffectiveBills = getBillsForCostEffectiveness();
        return costEffectiveBills.stream()
                .map(Bill::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public List<Bill> getBillsForOperativePlanning() {
        return billRepository.findByOperativePlanningCategory("your_operative_planning_category");
    }

    @Override
    public BigDecimal calculateTotalForOperativePlanning() {
        List<Bill> operativePlanningBills = getBillsForOperativePlanning();
        return operativePlanningBills.stream()
                .map(Bill::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public List<Bill> getBillsForViability() {
        return billRepository.findByViabilityCategory("your_viability_category");
    }

    @Override
    public BigDecimal calculateTotalForViability() {
        List<Bill> viabilityBills = getBillsForViability();
        return viabilityBills.stream()
                .map(Bill::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
