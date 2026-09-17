package com.example.backend.dto;

public class TtsResponse {
    private boolean success;
    private String audioUrl;
    private String message;

    public TtsResponse(boolean success, String audioUrl, String message) {
        this.success = success;
        this.audioUrl = audioUrl;
        this.message = message;
    }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getAudioUrl() { return audioUrl; }
    public void setAudioUrl(String audioUrl) { this.audioUrl = audioUrl; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}