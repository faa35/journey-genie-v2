package com.genie.journey_genie.controllers;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController

public class ApiKeysController {
    
    @Value("${LAST_FM_API_KEY}")
    private String lastFmApiKey;

    @Value("${LAST_FM_USERNAME}")
    private String lastFmUsername;

    @Value("${DISCORD_USER_ID}")
    private String discordUserId;

    @Value("${GEMINI_API_KEY}")
    private String geminiApiKey;

    @Value("${GOOGLE_MAPS_API_KEY}")
    private String googleMapsApiKey;

    @Value("${OPENAI_API_KEY}")
    private String openaiApiKey;


    @GetMapping("/api/details")
    public Map<String, String> getDetails() {
        return Map.of(
            "lastFmApiKey", lastFmApiKey,
            "lastFmUsername", lastFmUsername,
            "discordUserId", discordUserId,
            "geminiApiKey", geminiApiKey,
            "googleMapsApiKey", googleMapsApiKey,
            "openaiApiKey", openaiApiKey
        );
    }
}