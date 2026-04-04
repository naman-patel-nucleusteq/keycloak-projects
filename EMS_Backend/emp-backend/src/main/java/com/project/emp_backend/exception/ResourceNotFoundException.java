package com.project.emp_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//creating custom exception
@ResponseStatus(value = HttpStatus.NOT_FOUND)             //it tells spring When this exception is thrown, send this status code(ie 404(Not Found) ) in the response to client.      //@ResponseStatus is used to set the HTTP status code
public class ResourceNotFoundException extends RuntimeException {          //it's a custom exception that inherits properties of RuntimeException      //we don’t need to handle exception using try-catch every time, It can handle during runtime

    public ResourceNotFoundException(String message){      //constructor to pass custom error message, It runs when you throw this exception (and it takes a message as input). For eg:- throw new ResourceNotFoundException("Employee not found with id: 5");
        super(message);                                    //it calls or sends the message to the parent class (RuntimeException)
    }

}
