package com.cc.internalhack.InternalHack.dto;

import com.cc.internalhack.InternalHack.entity.enums.fileAccepted;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
public class HackDto {

    private String name;
    private Map<String, String> fields;
    private List<fileAccepted> acceptedFormats;
    private String location;
    private String organizer;
    private int maxMembers;
    private boolean isRegistrationOpen;
    private String contactEmail;
    private LocalDate startDate;
    private LocalDate endDate;
;
}
