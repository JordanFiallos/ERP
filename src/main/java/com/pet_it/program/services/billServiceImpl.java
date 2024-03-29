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
        return billRepository.findByCustomerId(customer.getId());
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
        LocalDate startOfMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate endOfMonth = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());

        return billRepository.findByIssueDateBetween(startOfMonth, endOfMonth);
    }

    @Override
    public List<Bill> getBillsForCostEffectiveness() {
        // Assuming bills with zero revenue are cost-effective
        return billRepository.findByRevenue(0);
    }

    @Override
    public BigDecimal calculateTotalForCostEffectiveness() {
        List<Bill> costEffectiveBills = getBillsForCostEffectiveness();
        return costEffectiveBills.stream()
                .map(Bill::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public List<Bill> getBillsForOperativePlanning() {
        // Assuming bills with negative revenue are operative planning
        return billRepository.findByRevenueLessThan(0);
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
        // Assuming bills with positive revenue are viability bills
        return billRepository.findByRevenueGreaterThan(0);
    }

    @Override
    public BigDecimal calculateTotalForViability() {
        List<Bill> viabilityBills = getBillsForViability();
        return viabilityBills.stream()
                .map(Bill::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
