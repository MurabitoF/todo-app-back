package com.example.todo.service

import com.example.todo.model.ToDoList
import com.example.todo.repository.ToDoListRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class ToDoListService(@Autowired val repo: ToDoListRepository) {

    fun getAll(): MutableList<ToDoList> {
        return repo.findAll()
    }

    fun getById(id: Int): ToDoList {
        return repo.findListById(id) ?: throw RuntimeException("To Do List not found!")
    }

    fun saveNewList(list: ToDoList): ToDoList {
        return repo.save(list)
    }

    fun updateTitle(id: Int,updatedList: ToDoList): ToDoList {
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