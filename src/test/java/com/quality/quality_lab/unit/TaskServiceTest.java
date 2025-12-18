package com.quality.quality_lab.unit;

import com.quality.quality_lab.application.TaskRepository;
import com.quality.quality_lab.application.TaskService;
import com.quality.quality_lab.domain.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;
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

    @Test
    void should_find_task_then_finalize_task_save_and_return_task(){
        Task taskReturnDone = createDoneValidTask();
        Optional<Task> taskReturn = Optional.of(createValidTask());
        ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);

        Mockito.doReturn(taskReturn).when(taskRepository).findById(Mockito.any());
        Mockito.doReturn(taskReturnDone).when(taskRepository).save(Mockito.any());

        Task task = taskService.finalizeTaskById(1L);

        Mockito.verify(taskRepository, Mockito.times(1)).findById(Mockito.any());
        Mockito.verify(taskRepository, Mockito.times(1)).save(taskCaptor.capture());

        Task taskSaved = taskCaptor.getValue();

        assertTrue(taskSaved.done());
        assertEquals(TITLE, taskSaved.title());
        assertEquals(1L, taskSaved.id());

        assertNotNull(task);
        assertEquals(TITLE, task.title());
        assertTrue(task.done());
    }

    @Test
    void should_throw_exception_when_mark_done_inexistent_task(){
        Mockito.doReturn(Optional.empty()).when(taskRepository).findById(Mockito.any());

        assertThrows(
                RuntimeException.class,
                () -> taskService.finalizeTaskById(1L)
        );

        Mockito.verify(taskRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void should_throw_exception_when_mark_done_already_done_task(){
        Task donedTask = createDoneValidTask();

        Mockito.doReturn(donedTask).when(taskRepository).findById(Mockito.any());

        assertThrows(
                RuntimeException.class,
                () -> taskService.finalizeTaskById(1L)
        );

        Mockito.verify(taskRepository, Mockito.never()).save(Mockito.any());
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
        return new Task(1L, TITLE, false, Instant.now());
    }

    private Task createDoneValidTask() {
        return new Task(1L, TITLE, true, Instant.now());
    }

}