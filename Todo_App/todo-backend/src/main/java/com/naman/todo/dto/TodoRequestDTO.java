package com.naman.todo.dto;

import com.naman.todo.enums.TodoStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class TodoRequestDTO {

    @NotNull(message = "Title is required")
    @Size(min = 3, message = "Title must have at least 3 characters")
    private String title;           
    private String description;
    private TodoStatus status;

    public TodoRequestDTO() {
    }

    public TodoRequestDTO(String title, String description, TodoStatus status) {
        this.title = title;
        this.description = description;
        this.status = status;
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


}
