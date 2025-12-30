package com.quality.quality_lab.integration;

import com.quality.quality_lab.application.TaskRepository;
import com.quality.quality_lab.infrastructure.repository.InMemoryRepository;
import com.quality.quality_lab.integration.repository.TaskRepositoryIntegrationTest;

public class TaskRepositoryInMemoryContractTest extends TaskRepositoryIntegrationTest {


    private final TaskRepository taskRepository = new InMemoryRepository();

    @Override
    public TaskRepository getTaskRepository() {
        return taskRepository;
    }
}
