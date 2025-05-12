package com.securetaskmanager.secure_task_manager_api.exception;

public class UnauthorizedTaskAccessException extends RuntimeException{

    public UnauthorizedTaskAccessException(String message) {
        super(message);
    }
}
