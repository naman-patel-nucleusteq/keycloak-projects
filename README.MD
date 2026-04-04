# StaffTrack – Employee Management System

**StaffTrack** is a full-stack Employee Management System that helps manage employee records efficiently. It provides a modern user interface and a robust backend powered by Spring Boot and Hibernate.

---

## Tech Stack

###  Frontend
- **React.js**
- **Tailwind CSS**
- **REST API Integration**

###  Backend
- **Spring Boot**
- **Spring Data JPA (Hibernate)**
- **RESTful APIs**

###  Database
- **MySQL**

---

##  Features
-  Add new employees  
-  View all employees  
-  Search employees  
-  Update employee details  
-  Delete employees  
-  Responsive UI with Tailwind CSS  
-  REST API integration between frontend & backend  

---

## 📁 Project Structure

```bash
Employee_Management_System/
│
├── frontend/
│   ├── components/        # UI components
│   ├── services/          # API calls
│   └── App.jsx            # Main React file
│
├── backend/
│   └── emp-backend/
│       └── src/
│           ├── controller/    # REST controllers
│           ├── dto/           # Data Transfer Objects
│           ├── entity/        # JPA entities
│           ├── repository/    # JPA repositories
│           ├── service/       # Business logic
│           ├── mapper/        # DTO ↔ Entity mapping
│           └── exception/     # Exception handling
│
└── README.md
```
---

##  Getting Started

Follow these steps to run the project locally.

---

### 1️. Clone the Repository

```bash
git clone https://github.com/Naman2166/Employee_Management_System.git
cd Employee_Management_System
```

---

### 2️. Setup Backend (Spring Boot + Hibernate)

```bash
cd ems_backend/emp-backend
```

#### Configure MySQL (`application.properties`)

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ems
spring.datasource.username=your_username
spring.datasource.password=your_password
```

#### Run Backend

```bash
mvn spring-boot:run
```

📍 Backend URL: `http://localhost:8080`

---

### 3. Setup Frontend (React + Tailwind)

```bash
cd ems_frontend
npm install
npm run dev
```

📍 Frontend URL: `http://localhost:3000`

---

### Prerequisites

Make sure you have installed:

- Node.js  
- Java (JDK 17 or above)  
- Maven  
- MySQL  

---
