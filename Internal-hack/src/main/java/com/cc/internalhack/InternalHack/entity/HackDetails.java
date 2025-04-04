package com.cc.internalhack.InternalHack.entity;

import com.cc.internalhack.InternalHack.entity.enums.fileAccepted;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.lang.annotation.After;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
@Getter
@Setter
public class HackDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Hack name cannot left blank")
    private String name;

    @ElementCollection
    @CollectionTable(name = "hack_fields", joinColumns = @JoinColumn(name = "hack_id"))
    @MapKeyColumn(name = "field_name")
    @Column(name = "field_value")
    private Map<String, String> fields = new HashMap<>();

    @ElementCollection(targetClass = fileAccepted.class,fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<fileAccepted> acceptedFormats;

    private String location;
    private String organizer;
    private int maxMembers;
    private boolean registrationOpen;
    private String contactEmail;

    @FutureOrPresent(message = "Start date must be in the future or today")
    private LocalDate startDate;

    @FutureOrPresent(message = "End date must be in the future or today")
    private LocalDate endDate;

    private LocalDateTime updatedAt;

    @PrePersist
    @PreUpdate
    private void validateDates(){

        LocalDate today = LocalDate.now();

        if(startDate == null || endDate == null){
            throw new RuntimeException("Start date and End date cannot be null");
        }

        if(endDate.isBefore(startDate)){
            throw new RuntimeException("End date cannote be before start Date");
        }
    }
}



