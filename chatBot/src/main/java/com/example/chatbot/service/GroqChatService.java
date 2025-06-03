package com.example.chatbot.service;

import com.example.chatbot.dto.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class GroqChatService {

    private static final Logger log = LoggerFactory.getLogger(GroqChatService.class);

    private final WebClient webClient;

    public GroqChatService(WebClient groqWebClient) {
        this.webClient = groqWebClient;
    }

    public String enviarMensaje(String mensaje) {
        // Crear el mensaje de usuario
        ChatMessage userMessage = new ChatMessage("user", mensaje);

        // Crear el request (aquí asumo que tienes una clase ChatRequest definida aparte)
        ChatRequest chatRequest = new ChatRequest();
        chatRequest.setModel("llama3-8b-8192"); // tu modelo
        chatRequest.setTemperature(0.7);
        chatRequest.setStream(false);
        chatRequest.setMessages(List.of(userMessage));

        // Loggear el JSON que se enviará
        ObjectMapper mapper = new ObjectMapper();
        try {
            log.info("Request sent to Groq: {}", mapper.writeValueAsString(chatRequest));
        } catch (JsonProcessingException e) {
            log.error("Error serializing chatRequest", e);
        }

        // Enviar el request y obtener la respuesta
        ChatResponse chatResponse = webClient.post()
                .uri("/chat/completions")
                .bodyValue(chatRequest)
                .retrieve()
                .bodyToMono(ChatResponse.class)
                .block();

        // Retornar la respuesta del chat (la primera opción)
        return chatResponse != null && chatResponse.getChoices() != null && !chatResponse.getChoices().isEmpty()
                ? chatResponse.getChoices().get(0).getMessage().getContent()
                : "No response from Groq API";
    }

    // Clases para mapear la respuesta JSON

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ChatResponse {
        private List<Choice> choices;

        public List<Choice> getChoices() {
            return choices;
        }

        public void setChoices(List<Choice> choices) {
            this.choices = choices;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Choice {
        private ChatMessage message;

        public ChatMessage getMessage() {
            return message;
        }

        public void setMessage(ChatMessage message) {
            this.message = message;
        }
    }


}
