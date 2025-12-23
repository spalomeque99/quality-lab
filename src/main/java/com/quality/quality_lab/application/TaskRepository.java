package com.quality.quality_lab.application;

import com.quality.quality_lab.domain.Task;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface TaskRepository {

    Task save(Task task);

    Optional<Task> findById(Long id);
}
