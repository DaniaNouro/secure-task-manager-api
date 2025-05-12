package com.securetaskmanager.secure_task_manager_api.service.service.impl;


import com.securetaskmanager.secure_task_manager_api.dto.TaskRequest;
import com.securetaskmanager.secure_task_manager_api.dto.TaskResponse;
import com.securetaskmanager.secure_task_manager_api.exception.TaskNotFoundException;
import com.securetaskmanager.secure_task_manager_api.exception.UnauthorizedTaskAccessException;
import com.securetaskmanager.secure_task_manager_api.model.Task;
import com.securetaskmanager.secure_task_manager_api.repository.TaskRepo;
import com.securetaskmanager.secure_task_manager_api.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class TaskServiceImpl implements TaskService {

        private final TaskRepo taskRepository;

    public TaskServiceImpl(TaskRepo taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
        public TaskResponse createTask(TaskRequest request, String userId) {
            Task task = new Task();
            task.setTitle(request.getTitle());
            task.setDescription(request.getDescription());
            task.setStatus(request.getStatus());
            task.setDueDate(request.getDueDate());
            task.setOwnerId(userId);

            Task saved = taskRepository.save(task);
            return mapToResponse(saved);
        }

    @Override
    public List<TaskResponse> getAllTasks(String userId) {
        List<Task> tasks = taskRepository.findByOwnerId(userId);
        return tasks.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponse getTaskById(Long taskId, String userId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task with ID " + taskId + " not found"));

        if (!task.getOwnerId().equals(userId)) {
            throw new UnauthorizedTaskAccessException("You are not authorized to access this task");
        }

        return mapToResponse(task);
    }

    @Override
    public TaskResponse updateTask(Long id, TaskRequest request, String userId) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task with ID " + id + " not found"));

        if (!task.getOwnerId().equals(userId)) {
            throw new UnauthorizedTaskAccessException("You do not have access to this task");
        }

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setDueDate(request.getDueDate());

        Task updated = taskRepository.save(task);
        return mapToResponse(updated);
    }


    @Override
    public void deleteTask(Long id, String userId) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task with ID " + id + " not found"));

        if (!task.getOwnerId().equals(userId)) {
            throw new UnauthorizedTaskAccessException("You do not have access to this task");
        }
        taskRepository.delete(task);
    }


        private TaskResponse mapToResponse(Task task) {
            TaskResponse response = new TaskResponse();
            response.setId(task.getId());
            response.setTitle(task.getTitle());
            response.setDescription(task.getDescription());
            response.setStatus(task.getStatus());
            response.setDueDate(task.getDueDate());
            return response;
        }

}
