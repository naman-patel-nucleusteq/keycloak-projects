import axios from "axios";
import { getValidToken } from "./keycloak"; 

const REST_API_BASE_URL = 'http://localhost:8081/api/employees'; 

const secureAxios = axios.create();

secureAxios.interceptors.request.use(
  async (config) => {
    const token = await getValidToken();
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

const listAllEmployees = () => {
    return secureAxios.get(REST_API_BASE_URL); 
}

const createEmployee = (employee) => {
    return secureAxios.post(`${REST_API_BASE_URL}/add`, employee); 
}

const getEmployeeById = (id) => {
    return secureAxios.get(`${REST_API_BASE_URL}/${id}`);
};

const updateEmployee = (id, employee) => {
    return secureAxios.put(`${REST_API_BASE_URL}/update/${id}`, employee); 
}

const deleteEmployee = (id) => {
    return secureAxios.delete(`${REST_API_BASE_URL}/delete/${id}`); 
}

export { listAllEmployees, createEmployee, updateEmployee, getEmployeeById, deleteEmployee };
