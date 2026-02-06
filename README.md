# Task Manager – Full Stack Application

A full-stack Task Manager application built using Spring Boot, MySQL, and JavaScript.
The application allows users to register, log in, and manage their tasks efficiently.

---

## Tech Stack

### Backend
- Java
- Spring Boot
- Spring Data JPA
- MySQL
- REST APIs

### Frontend
- HTML
- CSS
- JavaScript
- Fetch API

---

## Features
- User registration
- User-based task creation
- View tasks per user
- Update task status
- Delete tasks
- REST API integration with frontend

---
---

## How to Run the Project

### Backend Setup
1. Open backend in IntelliJ IDEA
2. Configure MySQL in `application.properties`
3. Run the Spring Boot application
4. Backend runs on `http://localhost:8080`

### Frontend Setup
1. Open `index.html` in a browser
2. Use the UI to manage tasks

---

## API Endpoints
- POST `/api/auth/register`
- POST `/api/auth/login`
- POST `/api/tasks/{email}`
- GET `/api/tasks/{email}`
- PUT `/api/tasks/{id}`
- DELETE `/api/tasks/{id}`

---

## Author
**Nikhil Navnath Salve**
