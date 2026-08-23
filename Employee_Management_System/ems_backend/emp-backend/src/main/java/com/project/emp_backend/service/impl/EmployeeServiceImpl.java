package com.project.emp_backend.service.impl;

import com.project.emp_backend.dto.EmployeeDto;
import com.project.emp_backend.entity.Employee;
import com.project.emp_backend.exception.ResourceNotFoundException;
import com.project.emp_backend.mapper.EmployeeMapper;
import com.project.emp_backend.repository.EmployeeRepository;
import com.project.emp_backend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Service               
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;      



    //create or add employee
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {               
        
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);         

        Employee savedEmployee = employeeRepository.save(employee);             
        EmployeeDto savedEmployeeDto = EmployeeMapper.mapToEmployeeDto(savedEmployee);

        return savedEmployeeDto;
    }



    //get employee by id
    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId)                  
                            .orElseThrow(() -> new ResourceNotFoundException("Employee Not Found with given Id : " + employeeId));       

        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);

        return employeeDto;
    }



    //get all employees
    @Override
    public List<EmployeeDto> getAllEmployees() {

        List<Employee> allEmployees = employeeRepository.findAll();      
        
        List<EmployeeDto> allEmployeesDto = allEmployees.stream().map((emp)-> EmployeeMapper.mapToEmployeeDto(emp)).toList();             
        return allEmployeesDto;
    }



    //update employee details
    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with this id : " + employeeId));

        employee.setFirstName(updatedEmployee.getFirstName());         
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());

        Employee savedUpdatedEmployee =  employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(savedUpdatedEmployee);
    }



    //Delete employee by id
    @Override
    public String deleteEmployee(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with this id : " + employeeId));

        employeeRepository.deleteById(employeeId);    

        return "Employee Deleted successfully";
    }


}
