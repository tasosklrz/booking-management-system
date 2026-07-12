package com.bookingmanagement.repository;

import com.bookingmanagement.model.BookableService;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Προσβαση στα δεδομενα των υπηρεσιων
 */
public interface BookableServiceRepository extends JpaRepository<BookableService, Long> {
}
