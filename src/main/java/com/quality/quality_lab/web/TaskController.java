package com.quality.quality_lab.web;

import com.quality.quality_lab.application.TaskService;
import com.quality.quality_lab.domain.Task;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/{title}")
    private ResponseEntity<Task> createTask(@PathVariable("title") String title) {
        return ResponseEntity.ok(taskService.createTask(title));
    }

    @PostMapping("/finalize/{id}")
    private ResponseEntity<Task> finalizeTask(@PathVariable("id") Long id) {
        return ResponseEntity.ok(taskService.finalizeTaskById(id));
    }
}
