package com.cc.internalhack.InternalHack.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TimelineRequestDto {

    private Long id;

    private String eventName; // e.g., "Submission Deadline", "Pitch Presentation"
    private LocalDateTime eventStartDate;
    private LocalDateTime eventEndDate;
}
