package com.example.langchain4jtest.service;


import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.ChatMessageDeserializer;
import dev.langchain4j.data.message.ChatMessageSerializer;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;




@Component
public class myChatMemoryStore implements ChatMemoryStore {

    private static final int MAX_HISTORY = 5;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Override
    public List<ChatMessage> getMessages(Object userId) {

        String redisKey = "chat_history:" + userId;
        ListOperations<String, String> ops = redisTemplate.opsForList();
        // 取历史
        List<String> range = ops.range(redisKey, 0, -1);
        List<ChatMessage> historyMessage = new ArrayList<>();
        for(String messageJSON : range){
            historyMessage.add(ChatMessageDeserializer.messageFromJson(messageJSON));
        }
        return historyMessage;

    }

    @Override
    public void updateMessages(Object userId, List<ChatMessage> newlist) {
        // 保留最新5轮（10条）
        String redisKey = "chat_history:" + userId;
        ListOperations<String, String> ops = redisTemplate.opsForList();
        for(ChatMessage newMessage:newlist){
            ops.rightPush(redisKey,ChatMessageSerializer.messageToJson(newMessage));
        }
        ops.trim(redisKey, Math.max(0, ops.size(redisKey) - MAX_HISTORY * 2), -1);
    }

    @Override
    public void deleteMessages(Object userId) {

        String redisKey = "chat_history:" + userId;
        redisTemplate.delete(redisKey);
    }
}
