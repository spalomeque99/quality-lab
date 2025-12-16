package com.quality.quality_lab.domain;

import java.time.Instant;

public record Task(Long id,
                    String title,
                   boolean done,
                   Instant createdAt) {
    public Task {

        if (title == null) {
            throw new IllegalArgumentException("title cannot be null");
        }
        if (title.isBlank()) {
            throw new IllegalArgumentException("title cannot be blank");
        }
        if (title.length() > 120) {
            throw new IllegalArgumentException("title length cannot be more than 120");
        }
        if (createdAt == null) {
            throw new IllegalArgumentException("createdAt cannot be null");
        }
    }
}
