package com.cc.internalhack.InternalHack.service;

import com.cc.internalhack.InternalHack.dto.TimelineRequestDto;
import com.cc.internalhack.InternalHack.entity.Timeline;
import com.cc.internalhack.InternalHack.exception.ResourceNotFoundException;
import com.cc.internalhack.InternalHack.repository.TimelineRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimelineServiceImpl implements TimelineService{

    private final ModelMapper modelMapper;
    private final TimelineRepository timelineRepository;

    @Override
    public Timeline createTimeline(TimelineRequestDto timelineRequestDto) {

        Timeline timeline = modelMapper.map(timelineRequestDto, Timeline.class);

        timelineRepository.save(timeline);

        return timeline;

    }

    @Override
    public Timeline getTimelineById(Long id) {

        Timeline timeline = timelineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Timeline not available for this id"));

        return timeline;
    }

    @Override
    public List<Timeline> getAllTimeline() {
        return timelineRepository.findAll();
    }

    @Override
    public Timeline getTimeline(String name) {
         return timelineRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Timeline not found with this name"));

    }
}
