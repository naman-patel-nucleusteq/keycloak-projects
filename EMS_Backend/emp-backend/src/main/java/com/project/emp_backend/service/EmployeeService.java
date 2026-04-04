package com.project.emp_backend.service;

import com.project.emp_backend.dto.EmployeeDto;

import java.util.List;


//Here we define only methods (and it's implementation is provided in EmployeeServiceImpl class)
public interface EmployeeService {

    //create employee method
    EmployeeDto createEmployee(EmployeeDto employeeDto);             //this method return data of type EmployeeDto, which sends to frontend through controller layer

    //get employee by id method
    EmployeeDto getEmployeeById(Long employeeId);

    //get All Employees method
    List<EmployeeDto> getAllEmployees();                            // it returns list of all employees

    //update employee by id
    EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee);          //this method takes employee id and updated information(ie information which needs to be updated) as a argument and returns updated employee DTO

    //Delete employee by id
    String deleteEmployee(Long employeeId);

}
