package com.example.todo.controller

import com.example.todo.DTO.AuthenticationRequest
import com.example.todo.DTO.AuthenticationResponse
import com.example.todo.model.User
import com.example.todo.security.JwtUtils
import com.example.todo.service.UserService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/auth")
class AuthController(@Autowired val service: UserService,
                     @Autowired val manager: AuthenticationManager,
                     @Autowired val jwt: JwtUtils,
                     @Autowired val encoder: PasswordEncoder
    ) {

    @PostMapping("/authenticate")
    fun createToken(@RequestBody @Valid request: AuthenticationRequest): ResponseEntity<AuthenticationResponse> {
        return try {
            manager.authenticate(UsernamePasswordAuthenticationToken(request.username, request.password))
            val user = service.loadUserByUsername(request.username)
            val token = jwt.generateToken(user)

            ResponseEntity.ok(AuthenticationResponse(token))
        } catch (e: BadCredentialsException) {
            ResponseEntity(HttpStatus.FORBIDDEN)
        }
    }

    @PostMapping("/register")
    fun registerUser(@RequestBody newUser: AuthenticationRequest): ResponseEntity<AuthenticationResponse> {
        if (service.usernameExist(newUser.username)){
            throw RuntimeException("The email is already registered")
        }
        val hashPassword = encoder.encode(newUser.password)
        val userDetails = service.saveNewUser(User(newUser.username, hashPassword, Collections.singleton(SimpleGrantedAuthority("user"))))

        return ResponseEntity.status(HttpStatus.CREATED).body(AuthenticationResponse(jwt.generateToken(userDetails)))
    }
}