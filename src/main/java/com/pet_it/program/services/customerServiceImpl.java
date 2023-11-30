package com.pet_it.program.services;

/**
 *
 * @author Houssam
 */
import com.pet_it.program.DAO.customerDAO;
import com.pet_it.program.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class customerServiceImpl implements customerService {
    @Autowired
    private customerDAO customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }

    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long customerId, Customer updatedCustomer) {
        // Implement logic to update customer details
        // ...
        return customerRepository.save(updatedCustomer);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }
}


