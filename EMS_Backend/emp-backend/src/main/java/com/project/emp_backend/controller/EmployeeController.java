package com.project.emp_backend.controller;

import com.project.emp_backend.dto.EmployeeDto;
import com.project.emp_backend.repository.EmployeeRepository;
import com.project.emp_backend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")                   //all the clients can call/access the API from frontend (ie no more cors issue)
@AllArgsConstructor
@RestController                            //it tells spring that this class will handle REST API requests
@RequestMapping("/api/employees")          //base url for all the request
public class EmployeeController {

    private EmployeeService employeeService;


    //Add Employee REST API
    @PostMapping("/add")                //if any post request comes on "/api/employees/add-employee" url, then this method will execute
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {       //when information is sent from frontend (usually in JSON), then @RequestBody annotation converts incoming JSON data into a Java object(ie employeeDto),so we can use it in backend method.....So basically @RequestBody is used to receive incoming data in your backend method.
        EmployeeDto savedEmployeeDto = employeeService.createEmployee(employeeDto);                 //calling saving layer (ie calling createEmployee method)
        return new ResponseEntity<>(savedEmployeeDto, HttpStatus.CREATED);                          //Send saved employee data back to frontend, with status: 201 CREATED
    }


/* ------------------------------------------------------------------------------------
  NOTE :-

 - ResponseEntity :- It is a generic class(works with different datatype) used to send a full HTTP response (ie Instead of just sending data, you send a complete response) from backend to frontend.
                     It lets you control Response data, HTTP status code and header......basically ResponseEntity = Response data + Status + Headers(optional)
 - Syntax :- ResponseEntity<DataType> variableName = new ResponseEntity<>(body, status);
       Eg :  ResponseEntity<String> response = new ResponseEntity<>("Success", HttpStatus.OK);

 - without ResponseEntity (only data sent, no status code) :-
      public EmployeeDto getEmployee() {
         return employeeDto;
      }

 - with ResponseEntity (data + status) :-
      public ResponseEntity<EmployeeDto> getEmployee() {             // here EmployeeDto is a type of data which this method returns
         return new ResponseEntity<>(employeeDto, HttpStatus.OK);


 ------- Full Flow ---------
    Frontend (JSON)
         ↓
    @RequestBody converts json → EmployeeDto
         ↓
    Controller
         ↓
    Service layer
         ↓
    Database
        ↓
    Return saved data
-----------------------------------------------------------------------------------------  */


    //Get Employee by id REST API
    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long employeeId){         // @PathVariable -> Used to get values from URL path (inside URL)
        EmployeeDto employeeDto =  employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);                        // (or)  return ResponseEntity.ok(employeeDto);
    }


    //Get All Employees REST API
    @GetMapping                                //same as @GetMapping("/") or @GetMapping("")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<EmployeeDto> allEmployeesDto =  employeeService.getAllEmployees();
        return ResponseEntity.ok(allEmployeesDto);
    }


    //Update employee REST API
    @PutMapping("/update/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long employeeId, @RequestBody EmployeeDto updatedEmployee){         //this method takes employee id and updated information(ie information which needs to be updated) as a argument and returns updated employee DTO
        EmployeeDto updatedEmployeeDto = employeeService.updateEmployee(employeeId, updatedEmployee);
        return ResponseEntity.ok(updatedEmployeeDto);
    }


    //Delete employee REST API
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId ){      //name of path variable and argument variable can be different also
        String responseMessage = employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok(responseMessage);
    }

//time :- 1:10

}
