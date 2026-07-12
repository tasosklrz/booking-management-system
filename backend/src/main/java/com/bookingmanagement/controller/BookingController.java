package com.bookingmanagement.controller;

import com.bookingmanagement.dto.CreateBookingRequest;
import com.bookingmanagement.dto.UpdateBookingStatusRequest;
import com.bookingmanagement.model.Booking;
import com.bookingmanagement.service.BookingService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        // Anagnwsh olwn twn krathsewn
        return bookingService.getAllBookings();
    }

    @GetMapping("/{bookingId}")
    public Booking getBookingById(@PathVariable Long bookingId) {
        return bookingService.getBookingById(bookingId);
    }

    @GetMapping("/customer/{customerId}")
    public List<Booking> getBookingsByCustomer(@PathVariable Long customerId) {
        return bookingService.getBookingsByCustomer(customerId);
    }

    @PostMapping
    public ResponseEntity<Booking> createBooking(@Valid @RequestBody CreateBookingRequest createBookingRequest) {
        // Dhmiourgia neas krathshs gia pelath kai yphresia
        Booking savedBooking = bookingService.createBooking(createBookingRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBooking);
    }

    @PatchMapping("/{bookingId}/status")
    public Booking updateBookingStatus(
            @PathVariable Long bookingId,
            @Valid @RequestBody UpdateBookingStatusRequest updateBookingStatusRequest) {
        // Enhmerwsh katastashs krathshs
        return bookingService.updateBookingStatus(bookingId, updateBookingStatusRequest.getBookingStatus());
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long bookingId) {
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.noContent().build();
    }
}
