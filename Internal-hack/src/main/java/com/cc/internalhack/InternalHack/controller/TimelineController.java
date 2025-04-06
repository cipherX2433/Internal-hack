package com.cc.internalhack.InternalHack.controller;

import com.cc.internalhack.InternalHack.dto.TimelineRequestDto;
import com.cc.internalhack.InternalHack.entity.Timeline;
import com.cc.internalhack.InternalHack.service.TimelineService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/timeline")
@RequiredArgsConstructor
public class TimelineController {

    private final ModelMapper modelMapper;
    private final TimelineService timelineService;

    @PostMapping
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Timeline> createTimeline(@RequestBody TimelineRequestDto timelineRequestDto){
        Timeline timeline = timelineService.createTimeline(timelineRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(timeline);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Timeline> getTimelineById(@PathVariable Long id){
        Timeline timeline = timelineService.getTimelineById(id);
        return ResponseEntity.ok(timeline);
    }

    @GetMapping
    public ResponseEntity<List<Timeline>> getAllTimeline(){
        List<Timeline> timeline = timelineService.getAllTimeline();

        return ResponseEntity.ok(timeline);
    }

    @GetMapping("/event/{eventName}")
    public ResponseEntity<Timeline> getTimeline(@PathVariable String eventName){
        Timeline timeline = timelineService.getTimeline(eventName);
        return ResponseEntity.ok(timeline);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Timeline> updateTimeline(@PathVariable Long id,
                                                   @RequestBody TimelineRequestDto timelineRequestDto
                                                    ){
        Timeline timeline = timelineService.updateTimeline(id, timelineRequestDto);
        return ResponseEntity.ok(timeline);
    }

}
