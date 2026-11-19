package com.suriya.taskmanager.config;

import com.suriya.taskmanager.entity.*;
import com.suriya.taskmanager.repository.ProjectRepository;
import com.suriya.taskmanager.repository.TaskRepository;
import com.suriya.taskmanager.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initDatabase(
        UserRepository userRepository,
        ProjectRepository projectRepository,
        TaskRepository taskRepository
    ) {
        return args -> {
            if (userRepository.count() == 0) {
                User admin = userRepository.save(new User(
                    "Admin User",
                    "admin@example.com",
                    "$2a$12$e8x/kO2bU9TfB/XgQ9sZ9u8zO7fC2e/O2n1m4l5k6j7h8g9f0e1d", // placeholder hash
                    Role.ADMIN
                ));

                User dev = userRepository.save(new User(
                    "Suriya Developer",
                    "suriya@example.com",
                    "$2a$12$e8x/kO2bU9TfB/XgQ9sZ9u8zO7fC2e/O2n1m4l5k6j7h8g9f0e1d",
                    Role.USER
                ));

                Project project = projectRepository.save(new Project(
                    "Platform Core Service",
                    "Production-grade task & workflow management backend service",
                    admin
                ));

                taskRepository.save(new Task(
                    "Setup Relational Database Schema",
                    "Initialize PostgreSQL entities, constraints, and audit timestamps",
                    TaskStatus.DONE,
                    TaskPriority.HIGH,
                    LocalDate.now().plusDays(3),
                    project,
                    admin,
                    dev
                ));
            }
        };
    }
}
