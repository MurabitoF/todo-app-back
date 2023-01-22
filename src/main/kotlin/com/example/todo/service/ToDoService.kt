package com.example.todo.service

import com.example.todo.model.ToDo
import com.example.todo.repository.ToDoRepository
import org.springframework.stereotype.Service

@Service
class ToDoService(val repo: ToDoRepository) {

    fun getAllByList(listId: Int): MutableList<ToDo> {
        return repo.findAllByToDoList(listId)
    }

    fun getById(idToDo: Int): ToDo {
        return repo.findToDoById(idToDo) ?: throw Exception("To Do not found!")
    }

    fun saveNewTodo(toDo: ToDo): ToDo {
        return repo.save(toDo)
    }

    fun updateTodo(idToDo: Int, updatedToDo: ToDo): ToDo {
        val oldToDo = this.getById(idToDo)

        if (oldToDo.title != updatedToDo.title) oldToDo.title = updatedToDo.title
        if (oldToDo.description != updatedToDo.description) oldToDo.description = updatedToDo.description
        if (oldToDo.completed != updatedToDo.completed) oldToDo.completed = updatedToDo.completed

        return repo.save(oldToDo)
    }

    fun deleteToDo(idToDo: Int): Int {
        val deleteToDo = this.getById(idToDo)
        repo.deleteById(idToDo)
        return deleteToDo.id
    }
}