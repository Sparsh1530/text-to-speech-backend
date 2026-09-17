package com.example.backend.service;

import com.example.backend.dto.TtsRequest;
import com.example.backend.dto.TtsResponse;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class TtsService {

    public TtsResponse generateSpeech(TtsRequest request) {
        try {
            String encodedText = URLEncoder.encode(request.getText(), StandardCharsets.UTF_8.toString());
            
            // Normalize language tags for Google TTS
            String langCode = request.getLanguage();
            if (langCode.startsWith("hi")) {
                langCode = "hi";
            } else if (langCode.contains("-")) {
                langCode = langCode.split("-")[0];
            }

            String googleTtsUrl = String.format(
                "https://translate.google.com/translate_tts?ie=UTF-8&q=%s&tl=%s&client=tw-ob",
                encodedText,
                langCode
            );

            URL url = new URL(googleTtsUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)");

            InputStream inputStream = connection.getInputStream();
            byte[] audioBytes = inputStream.readAllBytes();
            inputStream.close();

            String base64Audio = Base64.getEncoder().encodeToString(audioBytes);
            String dataUrl = "data:audio/mp3;base64," + base64Audio;

            return new TtsResponse(true, dataUrl, "Speech generated successfully!");
        } catch (Exception e) {
            return new TtsResponse(false, null, "Error generating audio: " + e.getMessage());
        }
    }
}