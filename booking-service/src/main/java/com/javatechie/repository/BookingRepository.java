package com.javatechie.repository;
import com.javatechie.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking findByBookingCode(String bookingId);
}
