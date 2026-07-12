package com.bookingmanagement.dto;

import com.bookingmanagement.model.BookingStatus;
import javax.validation.constraints.NotNull;

// Dedomena eisodou gia allagh katastashs krathshs
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
