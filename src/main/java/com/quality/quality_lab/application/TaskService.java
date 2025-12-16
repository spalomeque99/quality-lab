package com.quality.quality_lab.application;

import com.quality.quality_lab.domain.Task;

import java.time.Instant;
import java.util.Date;

public class TaskService {

    public Task createTask(String title) {
        return new Task(null, title, false, Instant.now());
    }
}
