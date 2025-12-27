package com.quality.quality_lab.infrastructure.repository;

import com.quality.quality_lab.application.TaskRepository;
import com.quality.quality_lab.domain.Task;
import com.quality.quality_lab.infrastructure.entity.TaskEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TaskRepositoryJpaAdapter implements TaskRepository {

    private final TaskJpaRepository taskJpaRepository;

    public TaskRepositoryJpaAdapter(TaskJpaRepository taskJpaRepository) {
        this.taskJpaRepository = taskJpaRepository;
    }


    @Override
    public Task save(Task task) {
        TaskEntity taskEntity = new TaskEntity(task.id(), task.title(), task.done(), task.createdAt());
        TaskEntity taskEntitySaved = taskJpaRepository.save(taskEntity);
        return this.toDomain(taskEntitySaved);
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskJpaRepository.findById(id).map(this::toDomain);
    }

    private Task toDomain(TaskEntity entity) {
        return new Task(
                entity.getId(),
                entity.getTitle(),
                entity.isDone(),
                entity.getCreatedAt()
        );
    }

}
