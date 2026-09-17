package com.example.backend.dto;

public class AuthResponse {
    private boolean success;
    private String message;
    private String name;
    private String email;

    public AuthResponse(boolean success, String message, String name, String email) {
        this.success = success;
        this.message = message;
        this.name = name;
        this.email = email;
    }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}