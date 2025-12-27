package com.quality.quality_lab.infrastructure.repository;

import com.quality.quality_lab.application.TaskRepository;
import com.quality.quality_lab.domain.Task;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryRepository implements TaskRepository {

    private final Map<Long, Task> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Task save(Task task) {
        Long id = task.id();

        if (id == null) {
            id = idGenerator.getAndIncrement();
        }

        Task taskToStore = new Task(
                id,
                task.title(),
                task.done(),
                task.createdAt()
        );

        storage.put(id, taskToStore);
        return taskToStore;
    }

    @Override
    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }
}
