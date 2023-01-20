package com.example.todo.DTO

import com.example.todo.model.ToDo
import com.example.todo.model.ToDoList
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class ToDoDTO(
    @NotBlank(message = "A title is required")
    val title: String,
    val description: String?,
    @NotNull(message = "A completed state is required")
    val completed: Boolean,
    @NotNull(message = "The to do must belong to a list")
    @Min(1, message = "The id should be a valid number")
    val listId: Int,
) {
    fun toDomain(toDoList: ToDoList) = ToDo(
        title = this.title,
        description = this.description.orEmpty(),
        completed = this.completed,
        toDoList = toDoList
    )
}
