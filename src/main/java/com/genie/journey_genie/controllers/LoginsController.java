// Java file for the logins controller

package com.genie.journey_genie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.genie.journey_genie.models.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;
import org.springframework.ui.Model;

import java.util.List;


@Controller
public class LoginsController {
    // Creating the repository object
    @Autowired
    private UserRepository repo;
    
    @Value("${GOOGLE_API_KEY}")
    private String GOOGLE_API_KEY;

    // Get request (displaying login page)
    @GetMapping("/login")
    public String displayLogin(Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
       // Finding the session
        User user = (User) session.getAttribute("sessionUser");
        
       // If user is null, return the login page
        if (user == null) {
            response.setStatus(200);
            return "loginPage";
        } else {
            return handleUserSession(user, model, response);
        }
    }

    // Get request (displaying the user/admin page)
    @GetMapping("/home")
    public String displayUserPageG(Model model, HttpServletResponse response, HttpSession session) {
        User user = (User) session.getAttribute("sessionUser");

       // If user is null, return the login page
        if (user == null) {
            response.setStatus(401);
            return "loginPage";
        } else {
            model.addAttribute("GOOGLE_API_KEY", GOOGLE_API_KEY);
            return handleUserSession(user, model, response);
        }
    }

    // Post request (displaying the user/admin page after attempting to log in)
    @PostMapping("/home")
    public String displayUserPage(@RequestParam Map<String, String> loginForm, Model model, HttpServletResponse response, HttpServletRequest request) {
        List<User> userList = repo.findByUsernameAndPassword(loginForm.get("username"), loginForm.get("password"));

        if (userList.isEmpty()) {
            response.setStatus(401);
            return "loginError";
        }

        User user = userList.get(0);
        request.getSession().setAttribute("sessionUser", user);
        model.addAttribute("GOOGLE_API_KEY", GOOGLE_API_KEY);
        return handleUserSession(user, model, response);
    }

    // Get request (displaying login page)
    @GetMapping("/logout")
    public String displayLogout(HttpServletResponse response, HttpServletRequest request) {
        // Destroy the session
        request.getSession().invalidate();
        response.setStatus(200);
        return "loginPage";
    }


    // Post request (displaying login page after logging out)
    @PostMapping("/logout")
    public String userLogout(HttpServletResponse response, HttpServletRequest request) {
        // Destroy the session
        request.getSession().invalidate();
        response.setStatus(200);
        return "loginPage";
    }


    // Get request (redirecting to /login)
    @GetMapping("/")
    public RedirectView redirect() {
        return new RedirectView("/login");
    }

    // Private method to handle user sessions based on their type
    private String handleUserSession(User user, Model model, HttpServletResponse response) {
        if (user.getType().toLowerCase().equals("admin")) {
            List<User> users = repo.findAll();
            model.addAttribute("users", users);
            response.setStatus(200);
            return "adminPage";
        } else {
            model.addAttribute("user", user);
            response.setStatus(200);
            return "userPage";
        }
    }
}
