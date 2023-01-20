package com.example.todo.repository

import com.example.todo.model.ToDo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ToDoRepository: JpaRepository<ToDo, Int> {
    fun findToDoById(id: Int): ToDo?
    fun findAllByToDoList(id: Int): MutableList<ToDo>
}