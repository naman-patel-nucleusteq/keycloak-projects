package com.project.emp_backend.service;

import com.project.emp_backend.dto.EmployeeDto;

import java.util.List;


public interface EmployeeService {

    //create employee method
    EmployeeDto createEmployee(EmployeeDto employeeDto);   
              
    //get employee by id method
    EmployeeDto getEmployeeById(Long employeeId);

    //get All Employees method
    List<EmployeeDto> getAllEmployees();                            

    //update employee by id
    EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee);        

    //Delete employee by id
    String deleteEmployee(Long employeeId);

}
