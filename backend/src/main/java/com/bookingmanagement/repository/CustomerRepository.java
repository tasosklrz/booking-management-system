package com.bookingmanagement.repository;

import com.bookingmanagement.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

// Prosvash sta dedomena twn pelatwn
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
