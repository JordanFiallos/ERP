package com.pet_it.program.services;

import com.pet_it.program.domain.Bill;
import com.pet_it.program.domain.Customer;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Houssam
 */

public interface billService {
    
    public Bill generateBill(Customer customer, BigDecimal amount);
    public List<Bill> getBillsByCustomer(Customer customer);
    public BigDecimal getTotalBillsForMonth();
    public List<Bill> getBillsForMonth();
    
}
