package com.securetaskmanager.secure_task_manager_api.controller;


import com.securetaskmanager.secure_task_manager_api.dto.TaskRequest;
import com.securetaskmanager.secure_task_manager_api.dto.TaskResponse;
import com.securetaskmanager.secure_task_manager_api.service.TaskService;
import com.securetaskmanager.secure_task_manager_api.util.UserUtils;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserUtils userUtils;

    public TaskController(TaskService taskService, UserUtils userUtils) {
        this.taskService = taskService;
        this.userUtils = userUtils;
    }

    @PostMapping
    @PreAuthorize("hasRole('client-user')")
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest request) {
        String userId = userUtils.getCurrentUserId();
        TaskResponse response = taskService.createTask(request, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response); // 201 Created
    }


    @GetMapping
    @PreAuthorize("hasRole('client-user')")
    public ResponseEntity<List<TaskResponse> >getTasks() {
        String userId = userUtils.getCurrentUserId();
        List<TaskResponse> responses = taskService.getAllTasks(userId);
        return ResponseEntity.ok(responses); // HTTP 200 OK

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('client-user')")
    public ResponseEntity<TaskResponse> getTask(@PathVariable Long id) {
        String userId = userUtils.getCurrentUserId();
        TaskResponse response = taskService.getTaskById(id, userId);
        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequest request) {
        String userId = userUtils.getCurrentUserId();
        TaskResponse response  =taskService.updateTask(id, request, userId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskResponse> deleteTask(@PathVariable Long id) {
        String userId = userUtils.getCurrentUserId();
        taskService.deleteTask(id, userId);
        return ResponseEntity.noContent().build();

    }

}
