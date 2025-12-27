package com.quality.quality_lab.integration;

import com.quality.quality_lab.application.TaskService;
import com.quality.quality_lab.domain.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.temporal.ChronoUnit;

@SpringBootTest
public class TaskIntegrationTest {

    @Autowired
    private TaskService taskService;

    private static String TITLE = "Titulo";

    @Test
    void should_create_task_and_return_it() {
        Task savedTask = taskService.createTask(TITLE);

        Assertions.assertNotNull(savedTask.id());
        Assertions.assertFalse(savedTask.done());
        Assertions.assertNotNull(savedTask.createdAt());
    }

    @Test
    void should_create_task_return_it_and_finalize(){
        Task savedTask = taskService.createTask(TITLE);

        Task finalizedTask = taskService.finalizeTaskById(savedTask.id());

        Assertions.assertNotNull(finalizedTask.id());
        Assertions.assertFalse(savedTask.done());
        Assertions.assertTrue(finalizedTask.done());
        Assertions.assertNotNull(savedTask.createdAt());
        Assertions.assertEquals(finalizedTask.title(), savedTask.title());
        //Se hace asi, porque al persistir hay fallos en milisegundos
        Assertions.assertEquals(finalizedTask.createdAt().truncatedTo(ChronoUnit.MILLIS), savedTask.createdAt().truncatedTo(ChronoUnit.MILLIS));
        Assertions.assertEquals(finalizedTask.id(), savedTask.id());
    }

    @Test
    void should_throw_exception_when_finalize_inexistent_task(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            taskService.finalizeTaskById(200L);
        });
    }

    @Test
    void should_throw_exception_when_finalize_finalized_task(){

        Task savedTask = taskService.createTask(TITLE);

        Task finalizedTask = taskService.finalizeTaskById(savedTask.id());

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            taskService.finalizeTaskById(finalizedTask.id());
        });
    }
}
