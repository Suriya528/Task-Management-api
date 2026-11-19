package com.suriya.taskmanager.service;

import com.suriya.taskmanager.dto.CreateTaskRequest;
import com.suriya.taskmanager.dto.TaskResponse;
import com.suriya.taskmanager.dto.UpdateTaskRequest;
import com.suriya.taskmanager.entity.Project;
import com.suriya.taskmanager.entity.Task;
import com.suriya.taskmanager.entity.TaskPriority;
import com.suriya.taskmanager.entity.User;
import com.suriya.taskmanager.exception.ResourceNotFoundException;
import com.suriya.taskmanager.repository.ProjectRepository;
import com.suriya.taskmanager.repository.TaskRepository;
import com.suriya.taskmanager.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository,
                       ProjectRepository projectRepository,
                       UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public TaskResponse createTask(CreateTaskRequest request) {
        Project project = projectRepository.findById(request.projectId())
            .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + request.projectId()));

        User createdBy = userRepository.findById(request.createdById())
            .orElseThrow(() -> new ResourceNotFoundException("Creator user not found with id: " + request.createdById()));

        User assignee = null;
        if (request.assigneeId() != null) {
            assignee = userRepository.findById(request.assigneeId())
                .orElseThrow(() -> new ResourceNotFoundException("Assignee user not found with id: " + request.assigneeId()));
        }

        TaskPriority priority = request.priority() != null ? request.priority() : TaskPriority.MEDIUM;

        Task task = new Task(
            request.title(),
            request.description(),
            null,
            priority,
            request.dueDate(),
            project,
            createdBy,
            assignee
        );

        Task savedTask = taskRepository.save(task);
        return TaskResponse.fromEntity(savedTask);
    }

    @Transactional(readOnly = true)
    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
        return TaskResponse.fromEntity(task);
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll().stream()
            .map(TaskResponse::fromEntity)
            .toList();
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> getTasksByProjectId(Long projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new ResourceNotFoundException("Project not found with id: " + projectId);
        }
        return taskRepository.findByProjectId(projectId).stream()
            .map(TaskResponse::fromEntity)
            .toList();
    }

    @Transactional
    public TaskResponse updateTask(Long id, UpdateTaskRequest request) {
        Task task = taskRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

        User assignee = null;
        if (request.assigneeId() != null) {
            assignee = userRepository.findById(request.assigneeId())
                .orElseThrow(() -> new ResourceNotFoundException("Assignee user not found with id: " + request.assigneeId()));
        }

        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setStatus(request.status());
        task.setPriority(request.priority());
        task.setDueDate(request.dueDate());
        task.setAssignee(assignee);

        Task updatedTask = taskRepository.save(task);
        return TaskResponse.fromEntity(updatedTask);
    }

    @Transactional
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new ResourceNotFoundException("Task not found with id: " + id);
        }
        taskRepository.deleteById(id);
    }
}
