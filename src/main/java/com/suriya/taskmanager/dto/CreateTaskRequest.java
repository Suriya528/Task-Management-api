package com.suriya.taskmanager.dto;

import com.suriya.taskmanager.entity.TaskPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreateTaskRequest(
    @NotBlank(message = "Title is required")
    @Size(max = 200, message = "Title must not exceed 200 characters")
    String title,

    String description,
    TaskPriority priority,
    LocalDate dueDate,

    @NotNull(message = "Project ID is required")
    Long projectId,

    @NotNull(message = "Creator user ID is required")
    Long createdById,

    Long assigneeId
) {}
