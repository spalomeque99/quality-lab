package com.quality.quality_lab.integration.repository;

import com.quality.quality_lab.application.TaskRepository;
import com.quality.quality_lab.domain.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Optional;


public abstract class TaskRepositoryIntegrationTest {

    public abstract TaskRepository getTaskRepository();

    @Test
    void should_save_task_and_return_it() {
        TaskRepository repo = getTaskRepository();

        Task taskSaved = repo.save(createValidTask());
        Assertions.assertNotNull(taskSaved.id());
        Assertions.assertFalse(taskSaved.done());
    }

    @Test
    void should_find_task_by_id_return_it(){
        TaskRepository repo = getTaskRepository();
        Task taskSaved = repo.save(createValidTask());

        Optional<Task> task = repo.findById(taskSaved.id());
        Assertions.assertTrue(task.isPresent());
        Assertions.assertEquals(taskSaved.title(), task.get().title());
    }

    @Test
    void should_empty_find_task_by_id_inexistent(){
        TaskRepository repo = getTaskRepository();

        Optional<Task> task = repo.findById(2000L);
        Assertions.assertTrue(task.isEmpty());
    }

    private Task createValidTask() {
        return new Task(null, "TITLE", false, Instant.now());
    }
}
