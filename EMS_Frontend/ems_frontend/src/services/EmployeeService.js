// calling REST API using axios

import axios from "axios";

const REST_API_BASE_URL = 'http://localhost:8080/api/employees'     //base url for springboot project (or for all REST API for employees resources)


// calling list All Employees REST API
const listAllEmployees = () => {
    return axios.get(REST_API_BASE_URL);        //sending GET request to backend for getting all employees data
}

// calling create Employee REST API
const createEmployee = (employee) => {
    return axios.post(`${REST_API_BASE_URL}/add`, employee);        //sending POST request, along with data or request body(ie employee data) to backend for creating/adding new employee
}

//calling get employee by id REST API                    // used in updateEmployee.jsx 
const getEmployeeById = (id) => {
    return axios.get(`${REST_API_BASE_URL}/${id}`);
  };

//calling update Employee REST API
const updateEmployee = (id, employee) => {
    return axios.put(`${REST_API_BASE_URL}/update/${id}`, employee);        //sending POST request to update employee deatils
}

//calling update Employee REST API
const deleteEmployee = (id) => {
    return axios.delete(`${REST_API_BASE_URL}/delete/${id}`);             //sending DELETE request to delete employee
}



export {listAllEmployees, createEmployee, updateEmployee, getEmployeeById, deleteEmployee};