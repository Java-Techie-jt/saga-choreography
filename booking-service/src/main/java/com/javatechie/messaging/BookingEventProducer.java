package com.javatechie.messaging;

import com.javatechie.common.KafkaConfigProperties;
import com.javatechie.events.BookingCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.javatechie.common.KafkaConfigProperties.MOVIE_BOOKING_EVENTS_TOPIC;

@Component
@Slf4j
public class BookingEventProducer {

    private KafkaTemplate<String, Object> template;

    public BookingEventProducer(KafkaTemplate<String, Object> template) {
        this.template = template;
    }

    public void publishBookingEvents(BookingCreatedEvent createdEvent) {
        try {
            log.info("Publishing booking event with id : {}", createdEvent.bookingId());
            template.send(MOVIE_BOOKING_EVENTS_TOPIC, createdEvent.bookingId(), createdEvent);
        } catch (Exception e) {
            log.error("Error while publishing booking event for id : {}",
                    createdEvent.bookingId(), e);
        }
    }
}
