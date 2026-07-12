package com.bookingmanagement.service;

import com.bookingmanagement.exception.ResourceNotFoundException;
import com.bookingmanagement.dto.CreateBookingRequest;
import com.bookingmanagement.model.BookableService;
import com.bookingmanagement.model.Booking;
import com.bookingmanagement.model.BookingStatus;
import com.bookingmanagement.model.Customer;
import com.bookingmanagement.repository.BookableServiceRepository;
import com.bookingmanagement.repository.BookingRepository;
import com.bookingmanagement.repository.CustomerRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;
    private final BookableServiceRepository bookableServiceRepository;

    public BookingService(
            BookingRepository bookingRepository,
            CustomerRepository customerRepository,
            BookableServiceRepository bookableServiceRepository) {
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
        this.bookableServiceRepository = bookableServiceRepository;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public List<Booking> getBookingsByCustomer(Long customerId) {
        // Filtrarisma krathsewn gia sygkekrimeno pelath
        return bookingRepository.findByCustomerCustomerId(customerId);
    }

    public Booking getBookingById(Long bookingId) {
        // Elegxos an yparxei h krathsh pou zhththhke
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking was not found"));
    }

    public Booking createBooking(CreateBookingRequest createBookingRequest) {
        // Syndesh ths neas krathshs me yparxonta pelath kai yphresia
        Customer customer = customerRepository.findById(createBookingRequest.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer was not found"));
        BookableService bookableService = bookableServiceRepository.findById(createBookingRequest.getServiceId())
                .orElseThrow(() -> new ResourceNotFoundException("Bookable service was not found"));

        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setBookableService(bookableService);
        booking.setBookingDateTime(createBookingRequest.getBookingDateTime());
        booking.setBookingStatus(BookingStatus.PENDING);

        return bookingRepository.save(booking);
    }

    public Booking updateBookingStatus(Long bookingId, BookingStatus bookingStatus) {
        // Allagh katastashs ths krathshs
        Booking existingBooking = getBookingById(bookingId);
        existingBooking.setBookingStatus(bookingStatus);
        return bookingRepository.save(existingBooking);
    }

    public void deleteBooking(Long bookingId) {
        // Diagrafh krathshs afou epivevaiwthei oti yparxei
        Booking existingBooking = getBookingById(bookingId);
        bookingRepository.delete(existingBooking);
    }
}
