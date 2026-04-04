package com.project.emp_backend.mapper;

//Whenever we create RestAPI, we need to convert Dto to Entity (for saving data) and Entity to Dto (for response)
//When client send data :- Dto -> Entity -> save in DB  , When we send response to client :- Entity -> Dto -> send to client
//Because Dto = only sends and receive data from client  ,  Entity = Actual Database Table used to save,delete, update data in DB

import com.project.emp_backend.dto.EmployeeDto;
import com.project.emp_backend.entity.Employee;

public class EmployeeMapper {

    //for mapping Entity -> Dto (ie copying data from Entity → DTO)
    public static EmployeeDto mapToEmployeeDto(Employee employee){
        return new EmployeeDto(
          employee.getId(),
          employee.getFirstName(),
          employee.getLastName(),
          employee.getEmail()
        );
    }

    //for mapping Dto -> Entity
    public static Employee mapToEmployee(EmployeeDto employeeDto){
        return new Employee(
          employeeDto.getId(),
          employeeDto.getFirstName(),
          employeeDto.getLastName(),
          employeeDto.getEmail()
        );
    }

}
