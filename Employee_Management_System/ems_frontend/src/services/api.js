import axios from 'axios';
import { getValidToken } from './keycloak'; 

// Create a configured Axios instance
const apiClient = axios.create({
  baseURL: 'http://localhost:8081', 
  headers: {
    'Content-Type': 'application/json',
  },
});

// Interceptor to inject token automatically before ANY request goes out
apiClient.interceptors.request.use(
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

export default apiClient;
