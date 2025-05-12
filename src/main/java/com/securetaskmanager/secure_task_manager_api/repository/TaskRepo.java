package com.securetaskmanager.secure_task_manager_api.repository;

import com.securetaskmanager.secure_task_manager_api.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task,Long> {

    List<Task> findByOwnerId(String ownerId);



}
