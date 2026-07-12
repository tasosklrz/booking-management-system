package com.bookingmanagement.repository;

import com.bookingmanagement.model.BookableService;
import org.springframework.data.jpa.repository.JpaRepository;

// Prosvash sta dedomena twn yphresiwn
public interface BookableServiceRepository extends JpaRepository<BookableService, Long> {
}
