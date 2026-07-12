package com.bookingmanagement.dto;

import com.bookingmanagement.model.BookingStatus;
import javax.validation.constraints.NotNull;

/**
 * Δεδομένα εισόδου για αλλαγή κατάστασης κράτησης
 */
public class UpdateBookingStatusRequest {

    @NotNull
    private BookingStatus bookingStatus;

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
