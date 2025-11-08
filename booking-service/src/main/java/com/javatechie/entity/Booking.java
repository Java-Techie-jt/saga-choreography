package com.javatechie.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
  @Id
  private Long id;
  private String bookingCode;
  private String showId;
  @ElementCollection
  private List<String> seatIds;
  private String userId;
  private String status; // PENDING, CONFIRMED, CANCELLED
  private Instant createdAt;
  private long amount;

  }
