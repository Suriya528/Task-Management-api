package com.suriya.taskmanager.dto;

import com.suriya.taskmanager.entity.Task;
import com.suriya.taskmanager.entity.TaskPriority;
import com.suriya.taskmanager.entity.TaskStatus;

import java.time.Instant;
import java.time.LocalDate;

public record TaskResponse(
    Long id,
    String title,
    String description,
    TaskStatus status,
    TaskPriority priority,
    LocalDate dueDate,
    Long projectId,
    String projectName,
    Long createdById,
    String createdByName,
    Long assigneeId,
    String assigneeName,
    Instant createdAt,
    Instant updatedAt
) {
    public static TaskResponse fromEntity(Task task) {
        return new TaskResponse(
            task.getId(),
            task.getTitle(),
            task.getDescription(),
            task.getStatus(),
            task.getPriority(),
            task.getDueDate(),
            task.getProject().getId(),
            task.getProject().getName(),
            task.getCreatedBy().getId(),
            task.getCreatedBy().getName(),
            task.getAssignee() != null ? task.getAssignee().getId() : null,
            task.getAssignee() != null ? task.getAssignee().getName() : null,
            task.getCreatedAt(),
            task.getUpdatedAt()
        );
    }
}
