package com.example.todo.controller

import com.example.todo.DTO.ToDoDTO
import com.example.todo.model.ToDo
import com.example.todo.service.ToDoListService
import com.example.todo.service.ToDoService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/todo")
class ToDoController(@Autowired val toDoService: ToDoService, @Autowired val listService: ToDoListService) {

    @PostMapping("")
    fun saveNewToDo(@Valid @RequestBody todo: ToDoDTO): ResponseEntity<ToDo> {
        val list = listService.getById(todo.listId)
        return ResponseEntity.status(201).body(toDoService.saveNewTodo(todo.toDomain(list)))
    }

    @PutMapping("/{id}")
    fun updateToDo(@PathVariable id: Int,
                   @Valid @RequestBody updatedToDo: ToDoDTO): ResponseEntity<ToDo> {
        val list = listService.getById(updatedToDo.listId)

        return ResponseEntity.ok(toDoService.updateTodo(id, updatedToDo.toDomain(list)))
    }

    @DeleteMapping("/{id}")
    fun deleteToDo(@PathVariable id: Int): ResponseEntity<Int>{
        return ResponseEntity.ok(toDoService.deleteToDo(id))
    }
}