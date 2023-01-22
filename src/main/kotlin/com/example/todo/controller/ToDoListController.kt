package com.example.todo.controller

import com.example.todo.model.ToDoList
import com.example.todo.model.User
import com.example.todo.service.ToDoListService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/list")
class ToDoListController(val service: ToDoListService) {

    @GetMapping("")
    fun getAllLists(@AuthenticationPrincipal auth: User): ResponseEntity<MutableList<ToDoList>> {
        return ResponseEntity.ok(service.getAll(auth))
    }

    @PostMapping("")
    fun saveNewList(@RequestBody @Validated list: ToDoList,
                    @AuthenticationPrincipal auth: User): ResponseEntity<ToDoList> {
        return ResponseEntity.status(201).body(service.saveNewList(list, auth))
    }

    @PutMapping("/{id}")
    fun updateList(@PathVariable id: Int, @RequestBody @Validated list: ToDoList): ResponseEntity<ToDoList> {
        return ResponseEntity.ok(service.updateTitle(id, list))
    }

    @DeleteMapping("/{id}")
    fun deleteList(@PathVariable id: Int): ResponseEntity<Int> {
        return ResponseEntity.ok(service.deleteList(id))
    }
}