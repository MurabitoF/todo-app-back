package com.example.todo.service

import com.example.todo.model.User
import com.example.todo.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(val repo: UserRepository) {

    fun usernameExist(username: String): Boolean {
        val user = repo.findByUsername(username)

        return user != null
    }

    fun saveNewUser(newUser: User): User {
        return repo.save(newUser)
    }
}