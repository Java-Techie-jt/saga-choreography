package com.javatechie;

import com.javatechie.events.SeatReservedEvent;
import com.javatechie.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.javatechie.common.KafkaConfigProperties.MOVIE_BOOKING_GROUP;
import static com.javatechie.common.KafkaConfigProperties.SEAT_RESERVED_TOPIC;

@Component
@Slf4j
public class MovieBookingListener {


    private BookingService service;

    public MovieBookingListener(BookingService service) {
        this.service = service;
    }

    @KafkaListener(topics = SEAT_RESERVED_TOPIC, groupId = MOVIE_BOOKING_GROUP)
    public void consumeSeatReserveEvents(SeatReservedEvent event){

        log.info("MovieBookingListener:: Consuming seatReserved event");

        if(event.reserved()){
            log.info("Booking process completed for bookingId: {}", event.bookingId());
        }else{
            //rollback
            log.info("Seat reservation failed for bookingId: {}", event.bookingId());
            service.handleBookingOnSeatReservationFailure(event.bookingId());
        }

    }
}
