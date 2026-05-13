# XYZ Bank Digital Banking System

## Project Overview

XYZ Bank Digital Banking System is a Spring Boot REST API application developed as part of a banking modernization assignment.

The application allows customers to:

* Register for a new bank account
* Login securely using JWT authentication
* View account overview details
* Upload identification documents
* Download uploaded documents

The system follows layered architecture and includes:

* REST APIs
* JWT Security
* Exception Handling
* Validation
* Swagger Documentation
* Async File Upload
* Unit Testing with JUnit & Mockito
* JaCoCo Code Coverage

---

# Technologies Used

| Technology      | Purpose                        |
| --------------- | ------------------------------ |
| Java 21         | Programming Language           |
| Spring Boot     | Backend Framework              |
| Spring Security | Authentication & Authorization |
| JWT             | Token-based Authentication     |
| Spring Data JPA | Database Operations            |
| MySQL           | Database                       |
| Maven           | Build Tool                     |
| Lombok          | Boilerplate Reduction          |
| Swagger OpenAPI | API Documentation              |
| JUnit 5         | Unit Testing                   |
| Mockito         | Mocking Framework              |
| JaCoCo          | Code Coverage                  |

---

# Features

## Authentication APIs

### Register Customer

Registers a new customer account.

Features:

* Generates username automatically
* Generates password automatically
* Generates bank account number
* Validates customer eligibility
* Uploads ID document asynchronously

### Login Customer

Authenticates customer using username and password.

Returns:

* JWT Token

---

# Customer APIs

## Account Overview

Returns:

* Account Number
* Account Type
* Currency
* Account Balance

---

# Document APIs

## Upload Document

Supports:

* PDF
* JPG
* JPEG
* PNG

Maximum Size:

* 2MB

## Download Document

Allows authenticated customer to download uploaded ID document.

---

# Security

The application uses JWT-based authentication.

Protected APIs require:

```text
Authorization: Bearer <JWT_TOKEN>
```

Security Features:

* Stateless Authentication
* JWT Validation
* Role-based Access
* Password Encryption using BCrypt

---

# Project Structure

```text
src/main/java
│
├── config
├── controller
├── dto
├── entity
├── enums
├── exception
├── repository
├── security
├── service
└── util
```

---

# API Documentation

Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

OpenAPI Docs:

```text
http://localhost:8080/v3/api-docs
```

---

# Database Configuration

Update database configuration in:

```text
src/main/resources/application.yml
```

Example:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/xyz_bank
    username: root
    password: root
```

---

# Running the Application

## Clone Repository

```bash
git clone <repository-url>
```

## Build Project

```bash
mvn clean install
```

## Run Application

```bash
mvn spring-boot:run
```

OR run the main class directly from IDE.

---

# Running Tests

Run all tests:

```bash
mvn test
```

Run with JaCoCo coverage:

```bash
mvn clean verify
```

---

# JaCoCo Coverage Report

Generated report location:

```text
target/site/jacoco/index.html
```

Open the HTML file in browser to view:

* Line Coverage
* Branch Coverage
* Package Coverage
* Class Coverage

---

# Testing

The project includes:

* Service Layer Tests
* Controller Layer Tests
* DTO Tests
* Security Tests
* Utility Tests
* Exception Handler Tests
* Configuration Tests

Frameworks Used:

* JUnit 5
* Mockito
* Spring MockMvc

---

# Validation Rules

## Registration Validation

* Customer must be above 18 years
* Only Netherlands and Belgium customers allowed
* Email must be valid
* Account type must be valid

## File Upload Validation

* Only PDF/JPG/JPEG/PNG allowed
* Maximum file size: 2MB

---

# Account Types Supported

* SAVINGS
* SALARY
* CURRENT

Accepted Inputs:

* savings
* saving
* saving account
* salary
* salary account
* current
* current account

---

# Future Improvements

Possible enhancements:

* Transaction APIs
* Fund Transfer APIs
* Docker Support
* Kubernetes Deployment
* CI/CD Pipeline
* Email Notifications
* Audit Logging
* Redis Caching

---

# Author

Chidananda L

Spring Boot Developer Assignment Project

---

# Conclusion

This project demonstrates:

* REST API Development
* Spring Boot Best Practices
* Secure JWT Authentication
* Exception Handling
* Async Processing
* Testing & Code Coverage
* Clean Layered Architecture

The application is production-style and follows modern enterprise backend development standards.
