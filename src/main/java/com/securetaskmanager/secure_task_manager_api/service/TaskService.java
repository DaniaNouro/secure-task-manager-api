package com.securetaskmanager.secure_task_manager_api.service;

import com.securetaskmanager.secure_task_manager_api.dto.TaskRequest;
import com.securetaskmanager.secure_task_manager_api.dto.TaskResponse;

import java.util.List;

public interface TaskService
{
    TaskResponse createTask(TaskRequest request, String userId);

    List<TaskResponse> getAllTasks(String userId);

    TaskResponse getTaskById(Long id, String userId);

    TaskResponse updateTask(Long id, TaskRequest request, String userId);

    void deleteTask(Long id, String userId);
}
