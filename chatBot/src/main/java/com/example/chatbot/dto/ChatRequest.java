package com.example.chatbot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;


import lombok.Data;
import java.util.List;

@Data
public class ChatRequest {
    private String model;
    private List<ChatMessage> messages;
    private Double temperature;
    private Boolean stream;
}
