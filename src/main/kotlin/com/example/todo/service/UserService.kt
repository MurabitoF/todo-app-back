package com.example.todo.service

import com.example.todo.model.User
import com.example.todo.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserService(@Autowired val repo: UserRepository): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return repo.findByUsername(username) ?: throw RuntimeException("User not found")
    }

    fun usernameExist(username: String): Boolean {
        val user = repo.findByUsername(username)

        return user != null
    }

    fun saveNewUser(newUser: User): User {
        return repo.save(newUser)
    }
}