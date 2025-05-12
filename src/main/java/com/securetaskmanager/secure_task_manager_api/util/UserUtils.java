package com.securetaskmanager.secure_task_manager_api.util;


import jakarta.servlet.ServletOutputStream;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class UserUtils {

    public String getCurrentUserId() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof Jwt jwt) {
            return jwt.getClaimAsString("sub"); // Keycloak user ID
        }
        throw new RuntimeException("Invalid token: no JWT found");
    }
}