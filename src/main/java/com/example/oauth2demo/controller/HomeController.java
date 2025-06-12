package com.example.oauth2demo.controller;

import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/token")
    @ResponseBody
    public Map<String, Object> token(OAuth2AuthorizedClient authorizedClient) {
        return Map.of(
                "access_token", authorizedClient.getAccessToken().getTokenValue(),
                "expires_at", authorizedClient.getAccessToken().getExpiresAt());
    }

    @GetMapping("/access-token")
    public String getAccessToken(
            @RegisteredOAuth2AuthorizedClient("keycloak") OAuth2AuthorizedClient authorizedClient,
            @AuthenticationPrincipal OidcUser oidcUser) {
        return authorizedClient.getAccessToken().getTokenValue();
    }

    @GetMapping("/tokens")
    public Map<String, Object> tokens(
            @RegisteredOAuth2AuthorizedClient("keycloak") OAuth2AuthorizedClient authorizedClient,
            @AuthenticationPrincipal OidcUser oidcUser) {
        return Map.of(
                "id_token", oidcUser.getIdToken().getTokenValue(),
                "access_token", authorizedClient.getAccessToken().getTokenValue(),
                "refresh_token", authorizedClient.getRefreshToken() != null
                        ? authorizedClient.getRefreshToken().getTokenValue()
                        : "No refresh token");
    }

    @GetMapping("/dashboard")
    public String dashboard(@RegisteredOAuth2AuthorizedClient("keycloak") OAuth2AuthorizedClient authorizedClient,
            @AuthenticationPrincipal OidcUser oidcUser, Model model) {
        // if (principal != null) {
        // model.addAttribute("name", principal.getAttribute("name"));
        // model.addAttribute("email", principal.getAttribute("email"));
        // }
        model.addAttribute("username", oidcUser.getPreferredUsername());
        model.addAttribute("accessToken", authorizedClient.getAccessToken().getTokenValue());
        return "dashboard";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}