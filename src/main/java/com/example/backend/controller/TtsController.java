package com.example.backend.controller;

import com.example.backend.dto.TtsRequest;
import com.example.backend.dto.TtsResponse;
import com.example.backend.entity.SpeechHistory;
import com.example.backend.repository.SpeechHistoryRepository;
import com.example.backend.service.TtsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/tts")
public class TtsController {

    @Autowired
    private TtsService ttsService;

    @Autowired
    private SpeechHistoryRepository historyRepository;

    @PostMapping
    public ResponseEntity<TtsResponse> generateSpeech(@RequestBody TtsRequest request) {
        TtsResponse response = ttsService.generateSpeech(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/history")
    public ResponseEntity<?> saveHistory(@RequestBody SpeechHistory history) {
        SpeechHistory saved = historyRepository.save(history);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/history/{email}")
    public ResponseEntity<List<SpeechHistory>> getHistory(@PathVariable String email) {
        return ResponseEntity.ok(historyRepository.findByUserEmailOrderByCreatedAtDesc(email));
    }

    @PutMapping("/history/{id}/favorite")
    public ResponseEntity<?> toggleFavorite(@PathVariable Long id) {
        return historyRepository.findById(id).map(item -> {
            item.setFavorite(!item.isFavorite());
            return ResponseEntity.ok(historyRepository.save(item));
        }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/history/{id}")
public ResponseEntity<?> deleteHistoryItem(@PathVariable Long id) {
    if (historyRepository.existsById(id)) {
        historyRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
}
}