package com.cc.internalhack.InternalHack.repository;

import com.cc.internalhack.InternalHack.entity.Timeline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TimelineRepository extends JpaRepository<Timeline, Long> {
    Optional<Timeline> findByeventName(String eventName);
}
