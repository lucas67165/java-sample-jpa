# Java Sample JPA

**Note: This is just my personal study project for learning Spring Boot and JPA.**

A simple Spring Boot application with JPA for data persistence that I created to learn and practice Java development concepts.

## Overview

This project is a basic Spring Boot application that I built to demonstrate Java persistence with Spring Data JPA. It implements a simple layered architecture with basic CRUD operations as part of my learning journey.

## Features

- Spring Data JPA for database access
- Basic authentication and authorization
- Simple validation
- Database migrations with Liquibase

## Technologies

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL/H2 Database
- Maven

## Getting Started

### Prerequisites

- Java 17
- Maven 3.8+

### Build and Run

```bash
# Build the project
./mvnw clean install

# Run the application
./mvnw spring-boot:run
```

### Configuration

Basic configuration is in `src/main/resources/application.properties`

## Testing

```bash
# Run all tests
./mvnw test
```

## Project Structure

- `controller`: API endpoints
- `service`: Business logic
- `repository`: Data access
- `entity`: JPA entities
- `config`: Application configuration

## Contributing

Please follow these code style guidelines:
- Class names: PascalCase
- Method/variable names: camelCase
- Constants: UPPER_SNAKE_CASE
- Package names: lowercase

## License

[Lucas Java learner]

---
*This repository is for educational purposes only and is not intended for production use.*
