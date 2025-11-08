package com.javatechie.controller;

import com.javatechie.request.BookingRequest;
import com.javatechie.response.BookingResponse;
import com.javatechie.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking-service")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // Endpoint to handle seat booking requests
    @PostMapping("/bookSeat")
    public ResponseEntity<?> bookSeat(@RequestBody BookingRequest request) {
        BookingResponse response = bookingService.bookSeats(request);
        return ResponseEntity.ok(response);
    }
}
