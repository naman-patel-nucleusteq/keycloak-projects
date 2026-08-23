# Keycloak Projects

This repository contains **two full-stack applications** secured under a **single Keycloak realm** with **two separate clients**, enabling **Single Sign-On (SSO)** across both apps.


<br>

## Projects Overview

| Project | Description | Frontend Port | Backend Port |
|-----|-----|-----|-----|
| **StaffTrack** (Employee Management) | Manage employee records with full CRUD operations | `3000` | `8081` |
| **TaskFlow** (Todo App) | Manage personal tasks with status tracking | `4000` | `9000` |

Both projects share a **single Keycloak instance** running on port `8080`, under the realm **`ems-realm`**, with each project registered as its own OAuth2 client.


<br>

## Folder Structure

**Basic Overview** :

```
keycloak_Projects/
│
├── Employee_Management_System/
├── Todo_App/
└── README.md
```


**Employee Management System** :
```bash
Employee_Management_System/
│
├── ems-frontend/
│   ├── src/
│   │   ├── components/
│   │   ├── services/
│   │   ├── App.jsx
│   │   ├── main.jsx
│   │   └── index.css
│   │
│   ├── Dockerfile
│   ├── index.html
│   └── package.json
│
│
├── ems_backend/
│   └── emp-backend/
│       ├── src/
│       │   ├── config/
│       │   ├── controller/
│       │   ├── dto/
│       │   ├── entity/
│       │   ├── exception/
│       │   ├── mapper/
│       │   ├── repository/
│       │   └── service/
│       │
│       ├── pom.xml
│       └── Dockerfile
│
└── docker-compose.yaml
```


**Todo_App** :
```bash
Todo_App/
│
├── todo-frontend/
│   ├── src/
│   │   ├── components/
│   │   ├── services/
│   │   ├── App.jsx
│   │   ├── main.jsx
│   │   └── index.css
│   │
│   ├── Dockerfile
│   ├── index.html
│   └── package.json
│
│
├── todo-backend/
│   ├── src/
│   │   ├── config/
│   │   ├── controller/
│   │   ├── dto/
│   │   ├── entity/
│   │   ├── exception/
│   │   ├── mapper/
│   │   ├── repository/
│   │   └── service/
│   │
│   ├── pom.xml
│   └── Dockerfile
│
└── docker-compose.yaml
```

---

## Features

### StaffTrack – Employee Management System

- Add new employees
- View all employees in a searchable list
- Search / filter employees
- Update and Delete employees
- Secured API endpoints via Keycloak JWT tokens
- Login / Logout using Keycloak SSO
- Responsive UI with Tailwind CSS
- Fully containerized with Docker Compose (hosts the shared Keycloak instance)

---

### TaskFlow – Todo Management Application

- Add new tasks with title and description
- View, Update and Delete tasks with details
- Toggle task status: **PENDING** → **COMPLETED**
- Secured API endpoints via Keycloak JWT tokens
- Login / Logout using Keycloak SSO
- Single Sign-On (SSO) — log in once, access both apps
- Containerized with Docker Compose (joins the shared EMS network)

---
<br>


## Keycloak Setup (Single Realm · Two Clients)

Both applications share **one Keycloak instance** (started by the EMS `docker-compose.yaml`) with a **single realm** and **two clients** — one for each application.

### Architecture

```
                   Keycloak  
            (http://localhost:8080)
                      ↓
                    Realm 
                 (ems-realm)
        ______________↓_______________
       |                              |
       ↓                              ↓
     Client                       Client
  (ems-client)                 (todo-client)
         
```

All users created under `ems-realm` can log in to **both** applications (SSO).

---

<br> 

##  Getting Started

Follow these steps to run the project locally.

---

### Step 1 - Clone the Repository

```bash
git clone https://github.com/Naman-patel-nucleusteq/keycloak_Projects.git
cd keycloak_Projects
```

---
<br> 

### Step 2 – Start Employee Management System

Keycloak is started as part of the **Employee Management System** `docker-compose.yaml`. Run it first:

```bash
cd Employee_Management_System
```

Configure .env file

```bash
# MySQL Database
MYSQL_ROOT_PASSWORD=root_password
MYSQL_DATABASE_NAME=ems_data

# Keycloak Database & Admin Credentials
KC_DB_USER=root
KC_DB_PASS=root_password
KC_ADMIN_USER=admin
KC_ADMIN_PASS=admin_password

# Spring Boot Database Credentials
SPRING_DB_USER=root
SPRING_DB_PASS=root_password
```

Run:

```bash
docker compose up --build -d
```

| Service | URL |
|---|---|
| Keycloak | http://localhost:8080 |
| EMS Backend | http://localhost:8081 |
| EMS Frontend | http://localhost:3000 |

---
<br> 

### Step 3 – Create the Realm

1. Open **http://localhost:8080** to access keycloak
2. log in with admin credentials: **Username** → `admin` | **Password** → `admin_password` (from .env)
3. Click the realm dropdown (top-left) → **Create Realm**
4. Set **Realm name** to `ems-realm`
5. Click **Create**

---
<br>

### Step 4 – Create Client 1: `ems-client` (Employee Management)

1. Inside `ems-realm` → **Clients** → **Create client**
2. Fill in:

| Field | Value |
|---|---|
| Client ID | `ems-client` |
| Client type | `OpenID Connect` |
| Client authentication | `OFF` (public client) |

3. **Valid redirect URIs**: `http://localhost:3000/*`
4. **Web origins**: `http://localhost:3000`
5. Click **Save**

---
<br>

### Step 5 – Create Client 2: `todo-client` (Todo App)

1. Inside `ems-realm` → **Clients** → **Create client**
2. Fill in:

| Field | Value |
|---|---|
| Client ID | `todo-client` |
| Client type | `OpenID Connect` |
| Client authentication | `OFF` (public client) |

3. **Valid redirect URIs**: `http://localhost:4000/*`
4. **Web origins**: `http://localhost:4000`
5. Click **Save**

---
<br>

### Step 6 – Create a Realm User

1. Inside `ems-realm` → **Users** → **Add user**
2. Set a **Username** (e.g., `testuser`) → **Create**
3. Go to the **Credentials** tab → **Set password** → turn off **Temporary**

> This single user can now log in to **both** StaffTrack and TaskFlow via SSO.

---
<br>

### Step 7 – Configure Applications

Update your frontend `keycloak.js` / config files with:

```js
// EMS Frontend (ems-client)
{
  url: 'http://localhost:8080',
  realm: 'ems-realm',
  clientId: 'ems-client'
}

// Todo Frontend (todo-client)
{
  url: 'http://localhost:8080',
  realm: 'ems-realm',
  clientId: 'todo-client'
}
```

---
<br>

## Setup & Running Locally

### Prerequisites

Make sure the following are installed:

| Tool | Version |
|---|---|
| Java (JDK) | 17 or above |
| Maven | 3.8+ |
| Node.js | 18+ |
| Docker & Docker Compose | Latest |

---
<br>

### 1. Employee Management System
---

#### **Option A : Docker (Recommended)**

```bash
cd Employee_Management_System
docker compose up --build -d
```

| Service | URL |
|---|---|
| Keycloak | http://localhost:8080 |
| EMS Backend | http://localhost:8081 |
| EMS Frontend | http://localhost:3000 |


<br> 

#### **Option B : Manual**

Backend:

```bash
cd Employee_Management_System/ems_backend/emp-backend
mvn spring-boot:run
```

Frontend:

```bash
cd Employee_Management_System/ems_frontend
npm install
npm run dev
```

---
<br> 
<br>

### 2. Todo Application

> **Start Employee Management System first** — it hosts the shared Keycloak instance and Docker network that the Todo App connects to.

---

#### **Option A : Docker (Recommended)**

```bash
cd Todo_App
docker compose up --build -d
```

| Service | URL |
|---|---|
| Todo Backend | http://localhost:9000 |
| Todo Frontend | http://localhost:4000 |

<br> 

#### **Option B : Manual**

**Backend:**

```bash
cd Todo_App/todo-backend
mvn spring-boot:run
```

**Frontend:**

```bash
cd Todo_App/todo-frontend
npm install
npm install keycloak-js@26.0.0
npm run dev
```

---
<br>

## Application Workflow

### Authentication Flow (both apps)

```
User visits App (EMS or Todo)
        │
        ▼
Keycloak Login Page  (http://localhost:8080/realms/ems-realm)
        │
        ▼
User authenticates → Keycloak issues JWT Token
        │
        ▼
Frontend stores token → sends as Authorization: Bearer <token> header
        │
        ▼
Backend validates JWT against Keycloak public keys (/protocol/openid-connect/certs)
        │
        ▼
Protected API responds with requested data
```

### SSO Flow (switching between apps)

```
User logs in to EMS  (via ems-client)
        │
        ▼
Keycloak session is created for ems-realm
        │
        ▼
User opens Todo App  (via todo-client)
        │
        ▼
Keycloak detects active session → auto-login (no password prompt)
        │
        ▼
Todo App receives JWT → user is fully authenticated
```

---
<br>

## Tech Stack Summary

| Layer | EMS – StaffTrack | Todo – TaskFlow |
|---|---|---|
| **Frontend** | React + Tailwind CSS | React + Vite + CSS |
| **Backend** | Spring Boot + Spring Data JPA | Spring Boot + Spring Data JPA |
| **Database** | MySQL (Docker container) | H2 (in-memory / file volume) |
| **Auth Client** | `ems-client` (Keycloak) | `todo-client` (Keycloak) |
| **Auth Protocol** | OpenID Connect / JWT | OpenID Connect / JWT |
| **Containerization** | Docker Compose (network host) | Docker Compose (joins EMS network) |

---
<br>

## Port Reference

| Service | Port | Notes |
|---|---|---|
| Keycloak | `8080` | Shared across both applications |
| EMS Backend | `8081` | Spring Boot REST API |
| EMS Frontend | `3000` | React app served via Nginx |
| Todo Backend | `9000` | Spring Boot REST API |
| Todo Frontend | `4000` | React app served via Nginx |
| MySQL | `3307` | Host-mapped (internal container port: 3306) |

---
<br> 

## Author

Naman Patel
