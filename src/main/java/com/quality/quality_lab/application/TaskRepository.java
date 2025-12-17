package com.quality.quality_lab.application;

import com.quality.quality_lab.domain.Task;

public interface TaskRepository {

    Task save(Task task);
}
