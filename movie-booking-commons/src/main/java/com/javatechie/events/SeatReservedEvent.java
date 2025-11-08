package com.javatechie.events;

public record SeatReservedEvent(String bookingId, boolean reserved, long amount) {}