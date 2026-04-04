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

@Service                //this class will contain business logic   //@Service tells spring to create a bean for this class (ie its object will manage by spring)
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;      //it's a variable that points to EmployeeRepository bean (or object) , spring manages that bean lifecycle automatically



    //create or add employee
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {               //we will receive employeeDto(containing employee information) from frontend when we create/add a new user, so we pass that employeesDto as a argument in createEmployee method

        //converting DTO -> Entity (for saving in DB)
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);           // to save employeeDto(ie employee details) in database we need to convert it into employee entity

        //Saving Entity to database
        Employee savedEmployee = employeeRepository.save(employee);              //we know that employeeRepository already extends jpaRepository entity(in employeeRepository.java),// so it already contains some inbuild methods like save(), saveALL(), findById(), etc
                                                                                 //so here we are saving employee entity into database using save() method and it returns the same object which is saved (ie employee)
        //converting saved Entity(ie savedEmployee) -> Dto (for responding back to frontend)
        EmployeeDto savedEmployeeDto = EmployeeMapper.mapToEmployeeDto(savedEmployee);

        return savedEmployeeDto;
    }



    //get employee by id
    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {

        //finding employee by id in DB
        Employee employee = employeeRepository.findById(employeeId)                   //it returns either employee or empty(not found), so to avoid error we use 1 more method ".orElseThrow"  to throw custom exception
                            .orElseThrow(() -> new ResourceNotFoundException("Employee Not Found with given Id : " + employeeId));         //if employee exists it return it(ie employee entity) and if not found it throws exception (using lambda function)

        //converting entity -> dto
        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);

        return employeeDto;
    }



    //get all employees
    @Override
    public List<EmployeeDto> getAllEmployees() {

        //finding all employees
        List<Employee> allEmployees = employeeRepository.findAll();       //it returns list of all employees (ie employee entities)

        //converting list of entities -> list of dto (here mapper method will not work directly bcoz its only works for single object as an argument, but here it is a list)
        List<EmployeeDto> allEmployeesDto = allEmployees.stream().map((emp)-> EmployeeMapper.mapToEmployeeDto(emp)).toList();              // stream() :- to start the stream , map() :- to perform operation on each object(ie converting entity -> dto) , toList() :- to collect result into list

        return allEmployeesDto;
    }



/* --------------------------------------------------
  NOTE :-
  - Streams :- It is used to process collections (like List, Set) in a clean and easy way

  (Common Operations in Streams :- )
  - stream() :- to start the stream
  - map() :- to perform operation on each object inside a list (ie converting entity -> dto)
  - filter() :- to filter/select data based on condition.
  - toList() :- to convert stream result into a List

   Eg    List<Integer> nums = List.of(1,2,3,4,5);

         List<Integer> result = nums.stream()
             .filter(n -> n % 2 == 0)    // selects only even numbers
             .map(n -> n * 2)            // multiply by 2
             .toList();                  // collect result into List



  - toList() :- immutable (ie list cannot be modified)
  - Collectors.toList() :- mutable (ie list can be modified)

   Eg    List<Integer> result = nums.stream()
               .map(n -> n * 2)
               .collect(Collectors.toList());     //now List "result" can be modified

-------------------------------------------------------*/


    //update employee details
    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {

        //finding employee by id
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with this id : " + employeeId));

        //updating information
        employee.setFirstName(updatedEmployee.getFirstName());         //setFirstName and getFirstName are the constructor, automatically created using lombok in Entity and DTO class file
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());

        //saving data
        Employee savedUpdatedEmployee =  employeeRepository.save(employee);

        //converting entity to Dto and returning it
        return EmployeeMapper.mapToEmployeeDto(savedUpdatedEmployee);
    }



    //Delete employee by id
    @Override
    public String deleteEmployee(Long employeeId) {

        //finding employee by id (only bcoz if employee not found, we can throw exception )
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with this id : " + employeeId));

        //deleting employee
        employeeRepository.deleteById(employeeId);       //this method returns nothing (ie void)

        return "Employee Deleted successfully";
    }


}
