package com.example.todo.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import kotlin.collections.List

@Entity
data class ToDoList(
    @Column(nullable = false)
    var title: String,
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "toDoList", cascade = [CascadeType.REMOVE], orphanRemoval = true)
    val todos: List<ToDo>?,
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    var user: User?,
    @CreatedDate
    val createdDate: LocalDateTime? = LocalDateTime.now(),
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0
)
