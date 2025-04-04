package com.cc.internalhack.InternalHack.repository;

import com.cc.internalhack.InternalHack.entity.HackDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HackRepository extends JpaRepository<HackDetails, Long> {
}
