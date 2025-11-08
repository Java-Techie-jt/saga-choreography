package com.javatechie.listener;

import com.javatechie.common.KafkaConfigProperties;
import com.javatechie.events.BookingCreatedEvent;
import com.javatechie.service.SeatInventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.javatechie.common.KafkaConfigProperties.MOVIE_BOOKING_EVENTS_TOPIC;
import static com.javatechie.common.KafkaConfigProperties.SEAT_EVENT_GROUP;

@Component
@Slf4j
public class SeatInventoryListener {


    private final SeatInventoryService seatInventoryService;

    public SeatInventoryListener(SeatInventoryService seatInventoryService) {
        this.seatInventoryService = seatInventoryService;
    }

    @KafkaListener(topics = MOVIE_BOOKING_EVENTS_TOPIC, groupId = SEAT_EVENT_GROUP)
    public void consumeBookingEvents(BookingCreatedEvent event) {
        // Logic to update seat inventory based on booking event
        log.info("SeatInventoryListener:: Consuming bookingCreated event for bookingId {}", event.bookingId());
        seatInventoryService.handleBooking(event);
    }
}
