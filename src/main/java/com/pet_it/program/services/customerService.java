package com.pet_it.program.services;

/**
 *
 * @author Houssam
 */
import com.pet_it.program.domain.Customer;
import java.util.List;

public interface customerService {
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long customerId);
    Customer addCustomer(Customer customer);
    Customer updateCustomer(Long customerId, Customer updatedCustomer);
    void deleteCustomer(Long customerId);
}
