package com.project.emp_backend.controller;

import com.project.emp_backend.dto.EmployeeDto;
import com.project.emp_backend.repository.EmployeeRepository;
import com.project.emp_backend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
@RestController                            
@RequestMapping("/api/employees")          
public class EmployeeController {

    private EmployeeService employeeService;


    //Add Employee REST API
    @PostMapping("/add")              
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {    
        EmployeeDto savedEmployeeDto = employeeService.createEmployee(employeeDto);                
        return new ResponseEntity<>(savedEmployeeDto, HttpStatus.CREATED);                      
    }


    //Get Employee by id REST API
    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long employeeId){       
        EmployeeDto employeeDto =  employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);                      
    }


    //Get All Employees REST API
    @GetMapping                             
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<EmployeeDto> allEmployeesDto =  employeeService.getAllEmployees();
        return ResponseEntity.ok(allEmployeesDto);
    }


    //Update employee REST API
    @PutMapping("/update/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long employeeId, @RequestBody EmployeeDto updatedEmployee){       
        EmployeeDto updatedEmployeeDto = employeeService.updateEmployee(employeeId, updatedEmployee);
        return ResponseEntity.ok(updatedEmployeeDto);
    }


    //Delete employee REST API
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId ){      
        String responseMessage = employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok(responseMessage);
    }

//time :- 1:10

}
