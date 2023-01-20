package com.example.todo.repository

import com.example.todo.model.ToDoList
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ToDoListRepository: JpaRepository<ToDoList, Int>  {
    fun findListById(id: Int): ToDoList?
}