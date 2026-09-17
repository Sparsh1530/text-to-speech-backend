package com.example.backend.repository;

import com.example.backend.entity.SpeechHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SpeechHistoryRepository extends JpaRepository<SpeechHistory, Long> {
    List<SpeechHistory> findByUserEmailOrderByCreatedAtDesc(String userEmail);
}