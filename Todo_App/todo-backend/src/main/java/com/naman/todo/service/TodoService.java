package com.naman.todo.service;

import com.naman.todo.dto.TodoRequestDTO;
import com.naman.todo.dto.TodoResponseDTO;
import com.naman.todo.entity.Todo;
import com.naman.todo.enums.TodoStatus;
import com.naman.todo.exception.InvalidStatusException;
import com.naman.todo.exception.TodoNotFoundException;
import com.naman.todo.repository.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final NotificationServiceClient notificationServiceClient;

    public TodoService(TodoRepository todoRepository, NotificationServiceClient notificationServiceClient) {
        this.todoRepository = todoRepository;
        this.notificationServiceClient = notificationServiceClient;
    }

    private static final Logger logger = LoggerFactory.getLogger(TodoService.class);



    // create new todo
    public TodoResponseDTO createTodo(TodoRequestDTO dto) {

        logger.info("Creating todo: {}", dto.getTitle());

        Todo todo = new Todo();

        todo.setTitle(dto.getTitle());
        todo.setDescription(dto.getDescription());
        todo.setCreatedAt(LocalDateTime.now());       

    
        if (dto.getStatus() == null) {             
            todo.setStatus(TodoStatus.PENDING);
        }
        else {
            todo.setStatus(dto.getStatus());
        }

        Todo saved = todoRepository.save(todo); 

        logger.info("Todo created with id: {}", saved.getId());


        notificationServiceClient.sendNotification("New TODO created with id: " + saved.getId());

        return mapToResponseDTO(saved);                 
    }



    // get all todos
    public List<TodoResponseDTO> getAllTodos() {

        logger.info("Fetching all todos");              

        return todoRepository.findAll()
                .stream()
                .map(todo -> mapToResponseDTO(todo))  
                .collect(Collectors.toList());            
    }



    // get todo by id
    public TodoResponseDTO getTodoById(Long id) {

        logger.info("Fetching todo with id: {}", id);         

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException("Todo not found with id: " + id ));     

        return mapToResponseDTO(todo);
    }



    // update todo
    public TodoResponseDTO updateTodo(Long id, TodoRequestDTO dto) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException("Todo not found with id: " + id ));

        logger.info("Updating todo with id: {}", id);
       
        todo.setTitle(dto.getTitle());
        todo.setDescription(dto.getDescription());

        if (dto.getStatus() != null) {
            TodoStatus currentStatus = todo.getStatus();  
            TodoStatus newStatus = dto.getStatus();         

            if (currentStatus == newStatus) {
                throw new InvalidStatusException("Status value is already " + currentStatus);
            }

            if ((currentStatus == TodoStatus.PENDING && newStatus == TodoStatus.COMPLETED) ||
                    (currentStatus == TodoStatus.COMPLETED && newStatus == TodoStatus.PENDING)) {
                todo.setStatus(newStatus);
            }
            else {
                throw new InvalidStatusException("Invalid status transition");
            }
        }

        Todo updated = todoRepository.save(todo);
        logger.info("Todo updated with id: {}", updated.getId());

        return mapToResponseDTO(updated);
    }



    // delete todo
    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException("Todo not found with id: " + id ));

        logger.info("Deleting todo with id: {}", id);

        todoRepository.delete(todo);
        logger.info("todo deleted with id: {}", id);
    }



    private TodoResponseDTO mapToResponseDTO(Todo todo) {
        return new TodoResponseDTO(
                todo.getId(),
                todo.getTitle(),
                todo.getDescription(),
                todo.getStatus(),
                todo.getCreatedAt()
        );
    }
}