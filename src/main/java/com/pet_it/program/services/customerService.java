package com.pet_it.program.services;

/**
 *
 * @author Houssam
 */
import com.pet_it.program.domain.Customer;
import java.util.List;

public interface customerService {
    public List<Customer> getAllCustomers();;
    public Customer getCustomerById(Long customerId);;
    public Customer addCustomer(Customer customer);;
    public Customer updateCustomer(Long customerId, Customer updatedCustomer);
    public void deleteCustomer(Long customerId);;
    
}
