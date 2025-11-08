package com.javatechie.service;

import com.javatechie.PaymentEventsProducer;
import com.javatechie.events.BookingPaymentEvent;
import com.javatechie.events.SeatReservedEvent;
import com.javatechie.exception.PaymentServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private PaymentEventsProducer eventsProducer;


    public void processPayment(SeatReservedEvent event) {

        try {
            log.info("Processing payment for bookingId: {}", event.bookingId());

            // Simulate payment failure scenario
            if (event.amount() > 2000) {
                log.info("Payment amount exceeds limit for bookingId: {}", event.bookingId());
                // failure events
                eventsProducer.publishPaymentFailureEvent(event);
                //throw new RuntimeException("Payment amount exceeds limit");
            }else{
                // success event
                kafkaTemplate.send("payment-events",
                        new BookingPaymentEvent(event.bookingId(), true, event.amount()));
                eventsProducer.publishPaymentSuccessEvent(event);
                log.info("✅ Payment successful for bookingId: {}", event.bookingId());

            }


        }catch (Exception e){
            log.error("❌ Payment failed for bookingId: {}. Reason: {}", event.bookingId(), e.getMessage());
            throw new PaymentServiceException("Payment processing failed for bookingId: " + event.bookingId());
        }
    }

}
