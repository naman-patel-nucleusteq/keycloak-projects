package com.project.emp_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//creating custom exception
@ResponseStatus(value = HttpStatus.NOT_FOUND)             
public class ResourceNotFoundException extends RuntimeException {        

    public ResourceNotFoundException(String message){      
        super(message);                                   
    }

}
