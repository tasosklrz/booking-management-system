package com.bookingmanagement.repository;

import com.bookingmanagement.model.Booking;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

// Prosvash sta dedomena twn krathsewn
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByCustomerCustomerId(Long customerId);
}
