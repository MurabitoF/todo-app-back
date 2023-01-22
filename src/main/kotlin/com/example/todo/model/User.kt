package com.example.todo.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime

@Entity
@Table(name = "users")
data class User(
    @Column(nullable = false, unique = true)
    private val username: String,

    @Column(nullable = false)
    @JsonIgnore
    private val password: String,

    @Enumerated(EnumType.STRING)
    val role: Role,

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = [CascadeType.REMOVE], orphanRemoval = true, fetch = FetchType.LAZY)
    val list: List<ToDoList>? = listOf(),

    @CreatedDate
    val createdDate: LocalDateTime? = LocalDateTime.now(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = 0
) : UserDetails {

    override fun getAuthorities() = listOf(SimpleGrantedAuthority(role.name))
    override fun getPassword() = password
    override fun getUsername() = username
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true
}
