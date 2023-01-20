package com.example.todo.DTO

import jakarta.validation.constraints.NotBlank

data class AuthenticationRequest(
    @NotBlank(message = "The request must contain a password")
    val username: String,
    @NotBlank(message = "The request must contain a password")
    val password: String,
)
