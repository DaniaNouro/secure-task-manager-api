package com.securetaskmanager.secure_task_manager_api.dto;



import com.securetaskmanager.secure_task_manager_api.enums.TasksStatus;
import com.securetaskmanager.secure_task_manager_api.model.Task;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;


public class TaskRequest {


    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    private TasksStatus status =TasksStatus.PENDING; // Default

    private LocalDate dueDate;

    public TaskRequest() {
    }

    public TaskRequest(String title, String description, TasksStatus status, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
    }

    public @NotBlank(message = "Title is required") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "Title is required") String title) {
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