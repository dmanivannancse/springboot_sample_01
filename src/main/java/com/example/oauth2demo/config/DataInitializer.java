package com.example.oauth2demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.oauth2demo.todo.entity.Todo;
import com.example.oauth2demo.todo.repository.TodoRepository;

@Configuration
public class DataInitializer {
    
    @Bean
    public CommandLineRunner loadData(TodoRepository todoRepository) {
        return args -> {
            todoRepository.save(new Todo("Buy groceries"));
            todoRepository.save(new Todo("Finish Spring Boot project"));
            todoRepository.save(new Todo("Read a book"));
            todoRepository.save(new Todo("Go for a walk"));
            todoRepository.save(new Todo("Prepare dinner"));
            todoRepository.save(new Todo("Call a friend", true));
            todoRepository.save(new Todo("Clean the house",true));
        };
    }
}
