package com.bookingmanagement.service;

import com.bookingmanagement.exception.ResourceNotFoundException;
import com.bookingmanagement.model.Customer;
import com.bookingmanagement.repository.CustomerRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long customerId) {
        // Elegxos an yparxei o pelaths pou zhththhke
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer was not found"));
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long customerId, Customer customerDetails) {
        // Enhmerwsh stoixeiwn yparxontos pelath
        Customer existingCustomer = getCustomerById(customerId);
        existingCustomer.setFullName(customerDetails.getFullName());
        existingCustomer.setEmail(customerDetails.getEmail());
        existingCustomer.setPhoneNumber(customerDetails.getPhoneNumber());
        return customerRepository.save(existingCustomer);
    }

    public void deleteCustomer(Long customerId) {
        // Diagrafh pelath afou epivevaiwthei oti yparxei
        Customer existingCustomer = getCustomerById(customerId);
        customerRepository.delete(existingCustomer);
    }
}
