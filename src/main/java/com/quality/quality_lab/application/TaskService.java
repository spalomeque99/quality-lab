package com.quality.quality_lab.application;

import com.quality.quality_lab.domain.Task;

import java.time.Instant;

public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(String title) {
        Task task = new Task(null, title, false, Instant.now());
        return taskRepository.save(task);
    }
}
