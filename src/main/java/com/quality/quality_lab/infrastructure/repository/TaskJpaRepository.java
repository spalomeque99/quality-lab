package com.quality.quality_lab.infrastructure.repository;

import com.quality.quality_lab.infrastructure.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskJpaRepository extends JpaRepository<TaskEntity, Long> {

}
