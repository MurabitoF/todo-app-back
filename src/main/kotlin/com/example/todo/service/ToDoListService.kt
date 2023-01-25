package com.example.todo.service

import com.example.todo.DTO.ToDoListDTO
import com.example.todo.model.ToDoList
import com.example.todo.model.User
import com.example.todo.repository.ToDoListRepository
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class ToDoListService(val repo: ToDoListRepository) {

    fun getAll(user: User): MutableList<ToDoList> {
        return repo.findAllByUser(user)
    }

    fun getById(id: Int): ToDoList {
        return repo.findListById(id) ?: throw RuntimeException("To Do List not found!")
    }

    fun saveNewList(list: ToDoList, user: User): ToDoList {
        list.user = user
        return repo.save(list)
    }

    fun updateTitle(id: Int,updatedList: ToDoListDTO): ToDoList {
        val oldList = this.getById(id)
        oldList.title = updatedList.title

        return repo.save(oldList)
    }

    fun deleteList(id: Int): Int {
        val deletedList = this.getById(id)
        repo.deleteById(deletedList.id)
        return deletedList.id
    }
}