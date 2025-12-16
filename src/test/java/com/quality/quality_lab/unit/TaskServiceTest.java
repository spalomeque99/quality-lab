package com.quality.quality_lab.unit;

import com.quality.quality_lab.application.TaskService;
import com.quality.quality_lab.domain.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    private final TaskService taskService = new TaskService();

    @Test
    void should_create_task_when_title_is_valid() {
        String title = "TestTitle";

        Task task = taskService.createTask(title);

        assertNotNull(task);
        assertEquals(title, task.title());
        assertFalse(task.done());
        assertNotNull(task.createdAt());
    }

    @Test
    void should_throw_exception_when_title_is_blank() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> taskService.createTask("   ")
        );

        assertEquals("title cannot be blank", ex.getMessage());
    }

    @Test
    void should_throw_exception_when_title_is_longer_than_120_chars() {
        String longTitle = "a".repeat(121);

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> taskService.createTask(longTitle)
        );

        assertEquals("title length cannot be more than 120", ex.getMessage());
    }
}