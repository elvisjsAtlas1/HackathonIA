package com.example.chatbot.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HackathonScraper {

    private static final String URL = "https://sites.google.com/view/sist-concursos2025-1/i-hackathon";

    private Document getDocument() throws IOException {
        return Jsoup.connect(URL).get();
    }

    public String obtenerObjetivo() {
        try {
            Document doc = getDocument();
            Elements elementos = doc.select("p, h2, h3"); // ajusta si es necesario

            StringBuilder objetivo = new StringBuilder();
            boolean agregar = false;
            for (Element el : elementos) {
                String texto = el.text();
                if (texto.toLowerCase().contains("objetivo")) {
                    agregar = true;
                    continue;
                }
                if (agregar && (texto.trim().isEmpty() || texto.matches("(?i)(premios|bases|reglas|agenda|temática|inscripción).*"))) {
                    break;
                }
                if (agregar) objetivo.append(texto).append("\n");
            }
            return objetivo.length() > 0 ? objetivo.toString().trim() : "No se encontró información de objetivo.";
        } catch (IOException e) {
            return "Error al obtener objetivo: " + e.getMessage();
        }
    }

    public String obtenerPremios() {
        try {
            Document doc = getDocument();
            Elements elementos = doc.select("p, h2, h3");

            StringBuilder premios = new StringBuilder();
            boolean agregar = false;
            for (Element el : elementos) {
                String texto = el.text();
                if (texto.toLowerCase().contains("premios")) {
                    agregar = true;
                    continue;
                }
                if (agregar && (texto.trim().isEmpty() || texto.matches("(?i)(objetivo|bases|reglas|agenda|temática|inscripción).*"))) {
                    break;
                }
                if (agregar) premios.append(texto).append("\n");
            }
            return premios.length() > 0 ? premios.toString().trim() : "No se encontró información de premios.";
        } catch (IOException e) {
            return "Error al obtener premios: " + e.getMessage();
        }
    }

    public String obtenerBases() {
        try {
            Document doc = getDocument();
            Elements elementos = doc.select("p, h2, h3");

            StringBuilder bases = new StringBuilder();
            boolean agregar = false;
            for (Element el : elementos) {
                String texto = el.text();
                if (texto.toLowerCase().contains("bases")) {
                    agregar = true;
                    continue;
                }
                if (agregar && (texto.trim().isEmpty() || texto.matches("(?i)(objetivo|premios|reglas|agenda|temática|inscripción).*"))) {
                    break;
                }
                if (agregar) bases.append(texto).append("\n");
            }
            return bases.length() > 0 ? bases.toString().trim() : "No se encontró información de bases.";
        } catch (IOException e) {
            return "Error al obtener bases: " + e.getMessage();
        }
    }

    // Puedes agregar más métodos para agenda, reglas, etc. siguiendo este patrón
}