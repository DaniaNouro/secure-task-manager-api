package com.securetaskmanager.secure_task_manager_api.dto;
import com.securetaskmanager.secure_task_manager_api.enums.TasksStatus;
import lombok.Data;

import java.time.LocalDate;

public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private TasksStatus status;
    private LocalDate dueDate;

    public TaskResponse() {
    }

    public TaskResponse(String title, String description, TasksStatus status, LocalDate dueDate, Long id) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TasksStatus getStatus() {
        return status;
    }

    public void setStatus(TasksStatus status) {
        this.status = status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}