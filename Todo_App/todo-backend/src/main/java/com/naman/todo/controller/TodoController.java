package com.naman.todo.controller;

import com.naman.todo.dto.TodoRequestDTO;
import com.naman.todo.dto.TodoResponseDTO;
import com.naman.todo.service.TodoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// this class Handles all API requests related to Todo
@RestController
@RequestMapping("/todos")
// @CrossOrigin(origins = "http://localhost:4000", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    
    private static final Logger logger = LoggerFactory.getLogger(TodoController.class);



    // API to create Todo
    @PostMapping
    public ResponseEntity<TodoResponseDTO> createTodo(@Valid @RequestBody TodoRequestDTO dto) {     
        logger.info("API called: Create Todo with title: {}", dto.getTitle());                    
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.createTodo(dto));      
    }



    // API to get all todos
    @GetMapping
    public ResponseEntity<List<TodoResponseDTO>> getAllTodos() {
        logger.info("API called: Get All Todos");                      
        return ResponseEntity.ok(todoService.getAllTodos());
    }

    // API to get todo by id
    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDTO> getTodoById(@PathVariable Long id) {
        logger.info("API called: Get Todo by id: {}", id);
        return ResponseEntity.ok(todoService.getTodoById(id));
    }

    // API to update todo
    @PutMapping("/{id}")
    public ResponseEntity<TodoResponseDTO> updateTodo(@PathVariable Long id, @Valid @RequestBody TodoRequestDTO dto) {
        logger.info("API called: Update Todo with id: {}", id);
        return ResponseEntity.ok(todoService.updateTodo(id, dto));
    }

    // API to delete todo
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id) {
        logger.info("API called: Delete Todo with id: {}", id);
        todoService.deleteTodo(id);
        return ResponseEntity.ok("Todo deleted successfully");
    }
}