package com.example.oauth2demo.todo.repository;

import com.example.oauth2demo.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}