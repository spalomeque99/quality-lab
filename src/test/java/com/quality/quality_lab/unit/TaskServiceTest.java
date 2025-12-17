package com.quality.quality_lab.unit;

import com.quality.quality_lab.application.TaskRepository;
import com.quality.quality_lab.application.TaskService;
import com.quality.quality_lab.domain.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    private TaskService taskService;

    private final static String TITLE = "TestTitle";

    @BeforeEach
    public void setUp() {
        taskService = new TaskService(taskRepository);
    }

    @ParameterizedTest
    @MethodSource("invalidTitles")
    void should_throw_exception_when_title_is_invalid_and_repository_not_called(String invalidTitle) {
        assertThrows(
                IllegalArgumentException.class,
                () -> taskService.createTask(invalidTitle)
        );
        Mockito.verify(taskRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void should_create_task_when_title_is_valid() {
        Task taskReturn = createValidTask();

        Mockito.doReturn(taskReturn).when(taskRepository).save(Mockito.any());

        Task task = taskService.createTask(TITLE);

        Mockito.verify(taskRepository, Mockito.times(1)).save(Mockito.any());
        assertNotNull(task);
        assertEquals(TITLE, task.title());
        assertFalse(task.done());
        assertNotNull(task.createdAt());
    }

    static Stream <String> invalidTitles() {
        return Stream.of(
                null,
                "",
                "   ",
                "a".repeat(301)
        );
    }

    private Task createValidTask() {
        return new Task(null, TITLE, false, Instant.now());
    }
}