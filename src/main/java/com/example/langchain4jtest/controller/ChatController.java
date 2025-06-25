package com.example.langchain4jtest.controller;

import com.example.langchain4jtest.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class ChatController {

    @Autowired
    public  ChatService chatService;

    //对应你业务的user_id
    public final static String user_id = "YOUR_USERID";
    @PostMapping("/send")
    public Map<String, String> sendMessage(@RequestBody Map<String, String> request, HttpServletRequest httpRequest) {
        String message = request.get("message");
        String userId = request.getOrDefault("userId", user_id);
        log.info(userId);
        String response = chatService.chat(message, userId);
        
        return Map.of(
            "message", message,
            "response", response,
            "timestamp", java.time.LocalDateTime.now().toString()
        );
    }

    @PostMapping("/new")
    public Map<String, String> createNewChat(@RequestBody Map<String, String> request, HttpServletRequest httpRequest) {
        String userId = request.getOrDefault("userId", user_id);
        chatService.createNewChat(userId);
        
        return Map.of(
            "status", "success",
            "message", "新对话已创建",
            "timestamp", java.time.LocalDateTime.now().toString()
        );
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "OK", "message", "聊天服务正常运行");
    }
} 