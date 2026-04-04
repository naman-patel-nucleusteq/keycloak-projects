package com.project.emp_backend.repository;

import com.project.emp_backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

//Note :-
// @Repository -> it tells spring that this class works/interact with database  (or)  its marks a class as a DAO(data access object) -> means it is responsible for fetching, updating, deleting, saving data from database
// If we extend a JpaRepository interface then we don't need to write @Repository annotation (because spring internally adds @Repository and also applied exception handling)
// syntax -> JpaRepository<EntityType, IdType>   (EntityType :- Entity class name (ie table class name) , IdType :- Type of Primary Key)

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
