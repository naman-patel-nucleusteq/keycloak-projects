package com.project.emp_backend.dto;

//Dto(Data transfer object) is used for sending data between client and server (ie between different layers (client -> controller -> service -> and back to controller and client))
//Entity is basically actual database table, it contains full information e.g. name, email, password....but in DTO we can control how much information we want to send and receive from client e.g. name and email only , no password (so it is used for security reason and keep API response clean)

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Long id;             // Explanation is in Employee.java (why using 'Long' instead of 'long' datatype)
    private String firstName;
    private String lastName;
    private String email;
}
