package com.cc.internalhack.InternalHack.service;

import com.cc.internalhack.InternalHack.dto.TimelineRequestDto;
import com.cc.internalhack.InternalHack.entity.Timeline;

import java.util.List;
import java.util.Optional;

public interface TimelineService {
    Timeline createTimeline(TimelineRequestDto timelineRequestDto);

    Timeline getTimelineById(Long id);

    List<Timeline> getAllTimeline();

    Timeline getTimeline(String eventName);
}
