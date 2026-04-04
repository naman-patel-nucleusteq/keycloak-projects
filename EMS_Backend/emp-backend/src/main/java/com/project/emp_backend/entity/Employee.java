package com.project.emp_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor                   //used to create no argument constructor for this class
@AllArgsConstructor                  //used to create parametrized constructor for this class
@Entity                              //used to mark a class as JPA entity, (ie it tells spring that this class will acts as a database table)
@Table(name = "employees")           //used to customize table details  (ie now table name will be saved as 'employees' in database)
public class Employee {

    @Id                              //marks column as primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)      //Automatically generates id and increments it, whenever new employee is added
    private Long id;                    //long -> primitive datatype(cannot store null value) ,  Long -> Wrapper class (stores null value)...when we send request from frontend, we don't send 'id'(bcoz its automatically generating here) so 'long' will give error, therefore we are using 'Long' Wrapper class

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String LastName;

    @Column(name = "email_id", nullable = false, unique = true)           // nullable=false -> null values not allowed,    unique=true  ->  each value should be unique
    private String email;
}
