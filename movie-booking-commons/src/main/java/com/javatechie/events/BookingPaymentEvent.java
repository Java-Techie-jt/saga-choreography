package com.javatechie.events;

public record BookingPaymentEvent(String bookingId, boolean paymentCompleted, long amount) {
}
