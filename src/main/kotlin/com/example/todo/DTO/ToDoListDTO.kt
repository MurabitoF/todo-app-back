package com.example.todo.DTO

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class ToDoListDTO(
    @NotNull(message = "The list must have an id")
    val id: Int,
    @NotBlank(message = "The list must have a title")
    val title: String,
)
