package com.naman.todo.entity;

import com.naman.todo.enums.TodoStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;


// this class represents todo entity which is mapped to DB table
@Entity
@Table(name = "todos")        
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                        

    @Column(nullable = false)
    private String title;                  

    private String description;

    @Enumerated(EnumType.STRING)
    private TodoStatus status;             

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;    



    // Default Constructor
    public Todo() {
    }

    // Parameterized Constructor with all fields
    public Todo(Long id, String title, String description, TodoStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
    }


    // Getters and Setters for each fields

    //id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    //title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    //description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    //status
    public TodoStatus getStatus() {
        return status;
    }

    public void setStatus(TodoStatus status) {
        this.status = status;
    }


    //createdAt
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}