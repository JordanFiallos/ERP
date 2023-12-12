package com.pet_it.program.services;

import com.pet_it.program.domain.Bill;
import com.pet_it.program.domain.Customer;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Houssam
 */
public interface billService {

    public Bill generateBill(Customer customer, BigDecimal amount);

    public List<Bill> getBillsByCustomer(Customer customer);

    public BigDecimal getTotalBillsForMonth();

    public List<Bill> getBillsForMonth();

    public List<Bill> getBillsForCostEffectiveness();

    public BigDecimal calculateTotalForCostEffectiveness();

    public List<Bill> getBillsForOperativePlanning();

    public BigDecimal calculateTotalForOperativePlanning();

    public List<Bill> getBillsForViability();

    public BigDecimal calculateTotalForViability();

}
