package com.quality.quality_lab.application;

import com.quality.quality_lab.domain.Task;

import java.time.Instant;
import java.util.Optional;

public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(String title) {
        Task task = new Task(null, title, false, Instant.now());
        return taskRepository.save(task);
    }

    public Task finalizeTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        if (task.done()) {
            throw new IllegalArgumentException("Task already done");
        }

        Task completedTask = new Task(
                task.id(),
                task.title(),
                true,
                task.createdAt()
        );

        return taskRepository.save(completedTask);
    }
}
