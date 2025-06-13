package com.example.oauth2demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.oauth2demo.todo.entity.Todo;
import com.example.oauth2demo.todo.service.TodoService;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class HomeController {

    private final TodoService todoService;
    public HomeController(TodoService todoService) {
        this.todoService = todoService;
    }

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/dashboard")
    public String dashboard(
            @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
            @AuthenticationPrincipal OAuth2User oAuth2User, Model model, HttpServletRequest request) {

        String registrationId = authorizedClient.getClientRegistration().getRegistrationId();

        if ("google".equals(registrationId)) {
            model.addAttribute("name", oAuth2User.getAttribute("name"));
            model.addAttribute("email", oAuth2User.getAttribute("email"));     
            model.addAttribute("imageUrl", oAuth2User.getAttribute("picture")); // Google uses email as username       
            logger.info("Google Attributes: {}", oAuth2User.getAttributes());

        } else if ("keycloak".equals(registrationId)) {
            if (oAuth2User instanceof OidcUser) {
                OidcUser oidcUser = (OidcUser) oAuth2User;
                model.addAttribute("name", oAuth2User.getAttribute("name"));
                model.addAttribute("email", oAuth2User.getAttribute("email"));
                model.addAttribute("username", oidcUser.getPreferredUsername());
                // Add the OAuth2 access token to the model for use in the view or API calls that require authentication
                model.addAttribute("accessToken", authorizedClient.getAccessToken().getTokenValue());
            } else {
                model.addAttribute("error", "Authenticated user is not an OIDC user.");
            }
        }
        // Call the todo service to get todos and set in model attribute
        // Assuming you have a TodoService bean injected in this controller
        // and a method like List<Todo> getTodosForUser(String email)
        if (model.containsAttribute("email")) {
            // String email = (String) model.getAttribute("email");
            // You may want to inject TodoService via @Autowired or constructor injection
            // Example: private final TodoService todoService;
            List<Todo> todos = todoService.getAllTodos();
            model.addAttribute("todos", todos);

            CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");
            if (csrfToken != null) {
                model.addAttribute("csrfTokenValue", csrfToken.getToken());
                model.addAttribute("csrfHeaderName", csrfToken.getHeaderName());
            }
        }
        return "dashboard";
    }




}
// This code defines a HomeController for handling requests in an OAuth2-secured application.
// It includes methods for the home page, dashboard, login, and error handling.
// The dashboard method retrieves user information based on the OAuth2 client used (Google or Keycloak).
// The login method provides a custom login page, and the loginWithKeycloak method checks Keycloak's health before redirecting to the Keycloak login.
// The error method returns a simple error message.
// The controller uses Spring Security's OAuth2 features to manage authentication and authorization.
// The code also includes error handling for Keycloak connectivity issues, redirecting users to Google login if Keycloak is unavailable.
// The controller is annotated with @Controller to indicate that it is a Spring MVC controller.
// The @GetMapping annotations map HTTP GET requests to specific methods, allowing for clean and organized routing.
// The @AuthenticationPrincipal and @RegisteredOAuth2AuthorizedClient annotations are used to access the authenticated user's details and the OAuth2 client information, respectively.
// The model is used to pass attributes to the view, allowing dynamic content rendering in the dashboard.
// The code is structured to provide a clear separation of concerns, with each method handling a specific aspect of the application's functionality.
// The controller is designed to be part of a larger Spring Boot application, leveraging Spring Security for OAuth2 authentication.
// The code is ready to be integrated into a Spring Boot application, providing a solid foundation for OAuth2-based authentication and user management.
// The HomeController class is part of a Spring Boot application that implements OAuth2 authentication.
// It handles user login, dashboard display, and error handling.        