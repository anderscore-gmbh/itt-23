package com.anderscore.testcontainers.demo.controller;

import com.anderscore.testcontainers.demo.entity.Task;
import com.anderscore.testcontainers.demo.entity.User;
import com.anderscore.testcontainers.demo.repository.TaskRepository;
import com.anderscore.testcontainers.demo.repository.UserRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class UserControllerLegacyTest {

    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @LocalServerPort
    private Integer port;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
        taskRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void shouldGetAllCustomers() {
        User user1 = new User("Daniel", "daniel@anderscore.com");
        User user2 = new User("Kerstin", "kerstin@anderscore.com");
        userRepository.saveAll(List.of(user1, user2));

        Task task1 = new Task("Vortrag vorbereiten", user1);
        Task task2 = new Task("Stand buchen", user2);
        taskRepository.saveAll(List.of(task1, task2));

        given()
                .contentType(JSON)
                .when()
                .get("/api/users")
                .then()
                .statusCode(200)
                .body(".", hasSize(2));
    }
}
