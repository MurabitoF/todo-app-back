package com.example.todo.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

@Entity
data class ToDo(
    @Column(nullable = false)
    var title: String,
    @Column(columnDefinition = "TEXT")
    var description: String,
    @Column(nullable = false)
    var completed: Boolean,
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "list_id")
    @JsonIgnore()
    val toDoList: ToDoList,
    @CreatedDate
    val createdDate: LocalDateTime? = LocalDateTime.now(),
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0
)
