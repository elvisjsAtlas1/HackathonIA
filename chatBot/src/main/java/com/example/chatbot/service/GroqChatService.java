package com.example.chatbot.service;

import com.example.chatbot.dto.*;
import com.example.chatbot.modelo.Catalogo;
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
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class GroqChatService {

    private static final Logger log = LoggerFactory.getLogger(GroqChatService.class);

    private final WebClient webClient;
    private final CatalogoService catalogoService;

    public GroqChatService(WebClient groqWebClient, CatalogoService catalogoService) {
        this.webClient = groqWebClient;
        this.catalogoService = catalogoService;
    }

    public String enviarMensaje(String mensaje, Long userId) {
        String mensajeLower = mensaje.toLowerCase();

        // 🔍 Si el mensaje se refiere a conocer la empresa, responder directamente
        if (mensajeLower.contains("conocer más sobre la empresa") ||
                mensajeLower.contains("infotel") ||
                mensajeLower.contains("quiénes son ustedes") ||
                mensajeLower.contains("qué es infotel")) {

            return "Infotel Perú es una empresa especializada en soluciones tecnológicas, ofreciendo desarrollo de software, " +
                    "soporte técnico y consultoría para impulsar tu transformación digital." +
                    "si quieres conocer mas sobre nosotros presiona el boton para que tengas mas informacion";}

        // 👇 Si no, usar el flujo normal con datos contextuales
        String datosContextuales = obtenerDatosContextuales(mensaje, userId);

        String prompt = """
        El usuario dijo: "%s"

        Datos del sistema:
        %s

        Responde de forma clara, útil y amigable.
        """.formatted(mensaje, datosContextuales);

        return enviarPromptAGroq(prompt);
    }



    private String obtenerDatosContextuales(String mensaje, Long userId) {
        mensaje = mensaje.toLowerCase();

        if (mensaje.contains("catalogo") || mensaje.contains("catálogo")) {
            String nombreCatalogo = extraerNombreCatalogo(mensaje);
            if (nombreCatalogo != null) {
                Optional<CatalogoDTO> optCatalogo = catalogoService.getAllCatalogos().stream()
                        .filter(c -> c.getNombre().toLowerCase().contains(nombreCatalogo))
                        .findFirst();

                if (optCatalogo.isPresent()) {
                    CatalogoDTO catalogo = optCatalogo.get();
                    return "Información del catálogo:\n" + convertirCatalogoATexto(catalogo);
                } else {
                    return "No se encontró ningún catálogo con el nombre '" + nombreCatalogo + "'.";
                }
            } else {
                // No especificaron catálogo, devolvemos listado general de catálogos reales
                List<CatalogoDTO> todosCatalogos = catalogoService.getAllCatalogos();
                if (todosCatalogos.isEmpty()) {
                    return "Actualmente no hay catálogos disponibles.";
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Tenemos los siguientes catálogos disponibles:\n");
                    for (CatalogoDTO c : todosCatalogos) {
                        sb.append("- ").append(c.getNombre())
                                .append(": ").append(c.getDescripcion())
                                .append("\n");
                    }
                    sb.append("¿Cuál catálogo deseas consultar con más detalle?");
                    return sb.toString();
                }
            }
        }

        // Otros casos por defecto...
        return "No se encontraron datos relevantes en el sistema para esta consulta.";
    }



    private String enviarPromptAGroq(String prompt) {
        ChatMessage userMessage = new ChatMessage("user", prompt);

        ChatRequest chatRequest = new ChatRequest();
        chatRequest.setModel("llama3-8b-8192");
        chatRequest.setTemperature(0.7);
        chatRequest.setStream(false);
        chatRequest.setMessages(List.of(userMessage));

        ObjectMapper mapper = new ObjectMapper();
        try {
            log.info("Request sent to Groq: {}", mapper.writeValueAsString(chatRequest));
        } catch (JsonProcessingException e) {
            log.error("Error serializing chatRequest", e);
        }

        ChatResponse chatResponse = webClient.post()
                .uri("/chat/completions")
                .bodyValue(chatRequest)
                .retrieve()
                .bodyToMono(ChatResponse.class)
                .block();

        return chatResponse != null && chatResponse.getChoices() != null && !chatResponse.getChoices().isEmpty()
                ? chatResponse.getChoices().get(0).getMessage().getContent()
                : "No response from Groq API";
    }
    private String convertirCatalogoATexto(CatalogoDTO catalogo) {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(catalogo.getId()).append("\n");
        sb.append("Nombre: ").append(catalogo.getNombre()).append("\n");
        sb.append("Descripción: ").append(catalogo.getDescripcion()).append("\n");
        sb.append("Productos:\n");
        if (catalogo.getProductos() == null || catalogo.getProductos().isEmpty()) {
            sb.append("  No hay productos en este catálogo.\n");
        } else {
            for (ProductoDTO p : catalogo.getProductos()) {
                sb.append("  - ID: ").append(p.getId())
                        .append(", Nombre: ").append(p.getNombre())
                        .append(", Descripción: ").append(p.getDescripcion())
                        .append(", Precio: ").append(p.getPrecio())
                        .append(", Disponible: ").append(p.isDisponible())
                        .append("\n");
            }
        }
        return sb.toString();
    }
    private String extraerNombreCatalogo(String mensaje) {
        String[] palabras = mensaje.split("\\s+");
        for (int i = 0; i < palabras.length; i++) {
            if (palabras[i].contains("catalogo") || palabras[i].contains("catálogo")) {
                if (i + 1 < palabras.length) {
                    return palabras[i + 1].toLowerCase();
                }
            }
        }
        return null;
    }



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
