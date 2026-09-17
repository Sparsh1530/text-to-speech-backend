package com.example.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "speech_history")
public class SpeechHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;

    @Column(length = 2000)
    private String textPrompt;

    private String language;
    private String voice;
    private boolean isFavorite = false;
    private LocalDateTime createdAt = LocalDateTime.now();

    public SpeechHistory() {}

    public SpeechHistory(String userEmail, String textPrompt, String language, String voice) {
        this.userEmail = userEmail;
        this.textPrompt = textPrompt;
        this.language = language;
        this.voice = voice;
    }

    public Long getId() { return id; }
    public String getUserEmail() { return userEmail; }
    public String getTextPrompt() { return textPrompt; }
    public String getLanguage() { return language; }
    public String getVoice() { return voice; }
    public boolean isFavorite() { return isFavorite; }
    public void setFavorite(boolean favorite) { isFavorite = favorite; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}