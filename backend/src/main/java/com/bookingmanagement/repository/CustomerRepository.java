package com.bookingmanagement.repository;

import com.bookingmanagement.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Προσβαση στα δεδομενα πελατών
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
