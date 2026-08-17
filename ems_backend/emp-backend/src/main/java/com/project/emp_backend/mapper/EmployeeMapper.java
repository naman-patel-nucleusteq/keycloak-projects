package com.project.emp_backend.mapper;


import com.project.emp_backend.dto.EmployeeDto;
import com.project.emp_backend.entity.Employee;

public class EmployeeMapper {

    //for mapping Entity -> Dto 
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
