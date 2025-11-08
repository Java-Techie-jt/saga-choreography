package com.javatechie.utils.mapper;

import com.javatechie.entity.Booking;
import com.javatechie.response.BookingResponse;

public class EntityToBookingResponseMapper {

    public static BookingResponse map(Booking booking) {
        return new BookingResponse(booking.getBookingCode(),
                booking.getStatus());
    }
}
