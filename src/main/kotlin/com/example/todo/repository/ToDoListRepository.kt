package com.example.todo.repository

import com.example.todo.model.ToDoList
import com.example.todo.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ToDoListRepository: JpaRepository<ToDoList, Int>  {
    fun findListById(id: Int): ToDoList?
    fun findAllByUser(user: User): MutableList<ToDoList>
}