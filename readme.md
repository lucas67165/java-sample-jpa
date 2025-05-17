# Java Sample JPA

A Spring Boot application that demonstrates best practices for building RESTful APIs with Spring Data JPA, Spring Security, and other modern Java technologies.

## Overview

This project is a sample Spring Boot application that provides a RESTful API for managing site entities. It demonstrates the implementation of a layered architecture with controllers, services, repositories, and entities, along with proper validation, exception handling, and security.

## Features

- RESTful API endpoints for CRUD operations
- Spring Data JPA for database access
- JWT-based authentication and authorization
- Validation and exception handling
- Swagger API documentation
- Liquibase for database migrations
- Comprehensive test coverage

## Technologies Used

- Java 17
- Spring Boot 2.7.4
- Spring Data JPA
- Spring Security
- JWT (JSON Web Token)
- MySQL (Production)
- H2 Database (Testing)
- Lombok
- MapStruct
- Liquibase
- Swagger/Springfox
- JUnit 5 & Mockito
- Maven

## Prerequisites

- Java 17 (JDK 17)
- Maven 3.8+ (or use the included Maven wrapper)
- MySQL 8.0+ for production database

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/lucas67165/java-sample-jpa.git
cd java-sample
```

### Build the Project

```bash
./mvnw clean install
```

Or with the Maven wrapper on Windows:

```bash
mvnw.cmd clean install
```

### Configure the Database

The application uses Spring Boot's standard configuration approach:

1. Main configuration is in `src/main/resources/application.properties` or `application.yml`
2. Database migrations are managed with Liquibase:
   - Main changelog: `src/main/resources/db/changelog/db.changelog-master.yaml`
   - Database properties: `src/main/resources/liquibase.properties`

### Run the Application

```bash
./mvnw spring-boot:run
```

## API Documentation

Once the application is running, you can access the Swagger UI to explore and test the API:

```
http://localhost:8080/swagger-ui/
```

### Available Endpoints

- `POST /api/sites` - Create a new site
- `GET /api/sites` - Get all sites
- `GET /api/sites/{id}` - Get a site by ID
- `PUT /api/sites/{id}` - Update an existing site
- `DELETE /api/sites/{id}` - Delete a site

## Testing

### Running Tests

Run all tests:
```bash
./mvnw test
```

Run a specific test class:
```bash
./mvnw test -Dtest=SiteServiceTest
```

Run a specific test method:
```bash
./mvnw test -Dtest=SiteServiceTest#findAll_shouldReturnAllSites
```

## Project Structure

The project follows a standard layered architecture:
- `controller`: REST API endpoints
- `service`: Business logic interfaces
- `service/impl`: Business logic implementations
- `repository`: Data access interfaces
- `entity`: JPA entities
- `data`: DTOs, requests, responses, and other data objects
- `mapper`: Object mappers (using MapStruct)
- `config`: Application configuration
- `exception`: Custom exceptions and exception handling
- `utils`: Utility classes

## Security

- The application uses Spring Security with JWT authentication
- JWT configuration is in the `config/jwt` package
- Security configuration is in the `config/security` package

## Contributing

Please follow the code style guidelines in the project:

- Class names should be in PascalCase (e.g., `UserService`)
- Method and variable names should be in camelCase (e.g., `getUserById`)
- Constants should be in UPPER_SNAKE_CASE (e.g., `MAX_RETRY_COUNT`)
- Package names should be in lowercase (e.g., `com.example.lucas.service`)

For more detailed guidelines, please refer to the project's internal documentation.

## License

[Lucas Java Leaner]
