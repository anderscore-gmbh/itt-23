package com.anderscore.testcontainers.demo.controller;

import com.anderscore.testcontainers.demo.entity.User;
import com.anderscore.testcontainers.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {

    private final UserRepository repo;

    public TaskController(UserRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/api/users")
    public List<User> findAll() {
        return repo.findAll();
    }
}
