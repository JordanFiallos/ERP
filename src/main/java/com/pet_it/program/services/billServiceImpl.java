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

/**
 *
 * @author Jordan
 */
public class billServiceImpl {
    @Autowired
    private billDAO billRepository;

    public Bill generateBill(Customer customer, BigDecimal amount) {
        Bill bill = new Bill();
        bill.setCustomer(customer);
        bill.setAmount(amount);
        bill.setIssueDate(LocalDate.now());
        return billRepository.save(bill);
    }

    public List<Bill> getBillsByCustomer(Customer customer) {
        return billRepository.findByCustomer(customer);
    }

    public BigDecimal getTotalBillsForMonth() {
        LocalDate now = LocalDate.now();
        LocalDate startOfMonth = now.withDayOfMonth(1);
        LocalDate endOfMonth = now.with(TemporalAdjusters.lastDayOfMonth());

        List<Bill> bills = billRepository.findByIssueDateBetween(startOfMonth, endOfMonth);
        return bills.stream().map(Bill::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
