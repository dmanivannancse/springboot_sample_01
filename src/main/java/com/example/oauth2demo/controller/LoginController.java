package com.example.oauth2demo.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @Value("${keycloak.connect.timeout:2000}")
    private int keycloakConnectTimeout;

    @Value("${keycloak.read.timeout:2000}")
    private int keycloakReadTimeout;

    @GetMapping("/login/keycloak")
    public String loginWithKeycloak(RedirectAttributes redirectAttributes) {
        logger.info("Attempting to connect to Keycloak for login...");
        // Attempt to connect to Keycloak's well-known endpoint to check if it's reachable
        // If Keycloak is reachable, redirect to Keycloak login
        // If Keycloak is not reachable, redirect to Google login with an error message
        logger.info("Keycloak connect timeout: {} ms, read timeout: {} ms", keycloakConnectTimeout, keycloakReadTimeout);
        logger.info("Checking Keycloak health at: http://localhost:8080/realms/myrealm/.well-known/openid-configuration");
        try {
            URL keycloakHealth = java.net.URI.create("http://localhost:8080/realms/myrealm/.well-known/openid-configuration").toURL();
            HttpURLConnection connection = (HttpURLConnection) keycloakHealth.openConnection();
            connection.setConnectTimeout(keycloakConnectTimeout);
            connection.setReadTimeout(keycloakReadTimeout);
            connection.setRequestMethod("GET");
            int code = connection.getResponseCode();

            if (code == 200) {
                return "redirect:/oauth2/authorization/keycloak";
            } else {
                redirectAttributes.addFlashAttribute("error", "Keycloak is not responding. Try Google login.");
                return "redirect:/login";
            }
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "Keycloak is unreachable. Please use Google login.");
            return "redirect:/login";
        }
    }


    @GetMapping("/error")
    @ResponseBody
    public String error() {
        return "An error occurred. Please try again.";
    }
}