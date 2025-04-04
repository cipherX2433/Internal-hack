package com.cc.internalhack.InternalHack.repository;

import com.cc.internalhack.InternalHack.entity.ProjectSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectSubmission, Long> {
}
