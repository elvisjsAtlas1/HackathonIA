package com.example.chatbot.controller;



import com.example.chatbot.service.HackathonScraper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hackathon")
public class HackathonController {

    private final HackathonScraper scraper;

    public HackathonController(HackathonScraper scraper) {
        this.scraper = scraper;
    }

    @GetMapping("/objetivo")
    public String objetivo() {
        return scraper.obtenerObjetivo();
    }

    @GetMapping("/premios")
    public String premios() {
        return scraper.obtenerPremios();
    }

    @GetMapping("/bases")
    public String bases() {
        return scraper.obtenerBases();
    }
}