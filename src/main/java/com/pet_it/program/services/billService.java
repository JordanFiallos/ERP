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
<<<<<<< 35b8b504eb0bb5ea2e88554ec7b154ce424c466d

=======
//@Service
>>>>>>> c6327644694c8f8e49a62ef55195affb0709a2f3
public interface billService {
    
    public Bill generateBill(Customer customer, BigDecimal amount);
    public List<Bill> getBillsByCustomer(Customer customer);
    public BigDecimal getTotalBillsForMonth();
    public List<Bill> getBillsForMonth();
    
}
