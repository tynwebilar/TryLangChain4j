package com.example.langchain4jtest.controller;

import com.example.langchain4jtest.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ChatController {

    @Autowired
    public  ChatService chatService;

    @PostMapping("/send")
    public Map<String, String> sendMessage(@RequestBody Map<String, String> request, HttpServletRequest httpRequest) {
        String message = request.get("message");
        String userId = request.getOrDefault("userId", httpRequest.getRemoteAddr());
        String response = chatService.chat(message, userId);
        
        return Map.of(
            "message", message,
            "response", response,
            "timestamp", java.time.LocalDateTime.now().toString()
        );
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "OK", "message", "聊天服务正常运行");
    }
} 