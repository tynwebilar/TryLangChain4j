package com.example.langchain4jtest.service;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {

    private final OpenAiChatModel chatModel;
    @Autowired
    private myChatMemoryStore myChatMemoryStore;


    //记忆化多轮聊天
    public String chat(String message, String userId)  {


        List<ChatMessage> historyMessage =  myChatMemoryStore.getMessages(userId);
        StringBuilder context = new StringBuilder();
        if (historyMessage != null) {
            for (ChatMessage h : historyMessage) {
                context.append(h.text()).append("\n");
            }
        }
        context.append("用户: ").append(message).append("\n客服助手:");

        // 发送给AI
        String aiResponse = chatModel.generate(context.toString());

        // 存入历史（用户+AI各一条）
        ChatMessage userMessage = new UserMessage(message);
        ChatMessage aiMessage = new AiMessage(aiResponse);
        List<ChatMessage> newMessage = new ArrayList<>();
        newMessage.add(userMessage);
        newMessage.add(aiMessage);
        myChatMemoryStore.updateMessages(userId,newMessage);
        // 保留最新5轮（10条）

        return aiResponse;
    }

    // 创建新对话
    public void createNewChat(String userId) {
        myChatMemoryStore.deleteMessages(userId);
    }

} 