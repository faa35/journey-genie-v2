package com.genie.journey_genie.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.genie.journey_genie.models.User;

@Controller
public class WeatherController {

    @Value("${WEATHER_API_KEY}")
    private String weatherApiKey;

    @Value("${GOOGLE_API_KEY}")
    private String GOOGLE_API_KEY;

    @GetMapping("/weather")
    public String showWeatherDashboard(Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
        // Finding the session
        User user = (User) session.getAttribute("sessionUser");

        // If user is null, return the login page
        if (user == null) {
            response.setStatus(401); // Unauthorized
            return "loginPage";
        }

        // Else show the weather dashboard
        model.addAttribute("weatherApiKey", weatherApiKey);
        model.addAttribute("GOOGLE_API_KEY", GOOGLE_API_KEY);
        return "weather";
    }
}
