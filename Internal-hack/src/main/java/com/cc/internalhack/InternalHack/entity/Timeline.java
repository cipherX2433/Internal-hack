package com.cc.internalhack.InternalHack.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Timeline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventName; // e.g., "Submission Deadline", "Pitch Presentation"

    @FutureOrPresent(message = "Start date must be in the future or today")
    private LocalDateTime eventStartDate;

    @FutureOrPresent(message = "End date must be in the future or today")
    private LocalDateTime eventEndDate;
}
