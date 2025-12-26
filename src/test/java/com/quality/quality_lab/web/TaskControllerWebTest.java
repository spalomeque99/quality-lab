package com.quality.quality_lab.web;

import com.quality.quality_lab.application.TaskService;
import com.quality.quality_lab.domain.Task;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.Instant;


@WebMvcTest(TaskController.class)
public class TaskControllerWebTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TaskService taskService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void should_create_task_with_valid_title_and_return_task() throws Exception {

        // Arrange
        Task returned = new Task(1L, "Titulo", false, Instant.now());
        Mockito.when(taskService.createTask(ArgumentMatchers.anyString())).thenReturn(returned);

        // Act + Assert (contrato HTTP)
        mockMvc.perform(post("/tasks/{title}", "prueba"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Titulo"))
                .andExpect(jsonPath("$.done").value(false))
                .andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    public void should_return_400_and_message_when_title_is_invalid() throws Exception {
        //Arrange
        Mockito.when(taskService.createTask(Mockito.anyString())).thenThrow(new IllegalArgumentException("title length cannot be more than 120"));

        // Act + Assert
        mockMvc.perform(post("/tasks/{title}", "a".repeat(130)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN_VALUE))
                .andExpect(content().string("title length cannot be more than 120"));
    }

    @Test
    public void should_create_task_and_finalize_it_and_return_task() throws Exception {
        //Arrange
        Task doneTask = this.createDoneTask();
        Mockito.when(taskService.finalizeTaskById(Mockito.any())).thenReturn(doneTask);

        // Act + Assert
        mockMvc.perform(post("/tasks/finalize/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.done").value(true));
    }

    @Test
    public void should_throw_exception_when_try_finalize_task_inexistent() throws Exception {
        //Arrange
        Mockito.when(taskService.finalizeTaskById(Mockito.anyLong())).thenThrow(new IllegalArgumentException("Task not found"));

        // Act + Assert
        mockMvc.perform(post("/tasks/finalize/{id}", 2L))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN_VALUE))
                .andExpect(content().string("Task not found"));
    }

    private Task createDoneTask() {
        return new Task(1L, "Titulo", true, Instant.now());
    }
}
