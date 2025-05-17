# Project Guidelines

This document provides guidelines and instructions for developing and maintaining this Spring Boot application.

## Build/Configuration Instructions

### Prerequisites
- Java 17 (JDK 17)
- Maven 3.8+ (or use the included Maven wrapper)
- MySQL 8.0+ for production database

### Building the Project
1. Clone the repository
2. Build the project using Maven:
   ```bash
   ./mvnw clean install
   ```
   Or with the Maven wrapper on Windows:
   ```bash
   mvnw.cmd clean install
   ```

### Configuration
The application uses Spring Boot's standard configuration approach:

1. Main configuration is in `src/main/resources/application.properties` or `application.yml`
2. Database migrations are managed with Liquibase:
   - Main changelog: `src/main/resources/db/changelog/db.changelog-master.yaml`
   - Database properties: `src/main/resources/liquibase.properties`

### Running the Application
```bash
./mvnw spring-boot:run
```

## Code Style Guidelines

### Naming Conventions
- Class names should be in PascalCase (e.g., `UserService`)
- Method and variable names should be in camelCase (e.g., `getUserById`)
- Constants should be in UPPER_SNAKE_CASE (e.g., `MAX_RETRY_COUNT`)
- Package names should be in lowercase (e.g., `com.example.lucas.service`)

### Code Organization
- Keep methods short and focused on a single responsibility
- Limit line length to 120 characters
- Use 4 spaces for indentation (not tabs)
- Group related methods together
- Place fields at the top of the class, followed by constructors, then methods

### Documentation
- Add JavaDoc comments for all public classes and methods
- Include parameter descriptions and return value descriptions in JavaDoc
- Use `@author` and `@since` tags in class-level JavaDoc

## Spring Boot Specific Guidelines

### Controller Design
- Use `@RestController` for API endpoints
- Group related endpoints in the same controller
- Use appropriate HTTP methods (GET, POST, PUT, DELETE)
- Return appropriate HTTP status codes
- Validate input using `@Valid` annotation

### Service Layer
- Implement business logic in service classes
- Use interfaces for services
- Annotate service implementations with `@Service`
- Use constructor injection for dependencies

### Repository Layer
- Use Spring Data JPA repositories when possible
- Create custom query methods following Spring Data naming conventions
- Use `@Query` annotation for complex queries

### Exception Handling
- Create custom exception classes for different error scenarios
- Use `@ControllerAdvice` for global exception handling
- Return consistent error responses

## Testing Information

### Testing Framework
The project uses:
- JUnit 5 (Jupiter) for unit testing
- Mockito for mocking dependencies
- Spring Boot Test for integration testing
- H2 in-memory database for testing

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

### Writing Tests

#### Unit Tests
For unit tests, follow these guidelines:
1. Write unit tests for all service methods
2. Use the `@ExtendWith(MockitoExtension.class)` annotation for Mockito integration
3. Mock dependencies using `@Mock` annotation
4. Follow the Given-When-Then pattern (Arrange-Act-Assert)
5. Name tests descriptively, indicating what they test and expected outcome
6. Place tests in the same package structure as the classes they test
7. Mock external dependencies
8. Aim for high test coverage

Example unit test for a service:

```java
@ExtendWith(MockitoExtension.class)
public class SiteServiceTest {

    @Mock
    private SiteRepository siteRepository;

    private SiteService siteService;

    @BeforeEach
    void setUp() {
        siteService = new SiteServiceImpl(siteRepository);
    }

    @Test
    void findAll_shouldReturnAllSites() {
        // Given
        List<Site> expectedSites = Arrays.asList(
            Site.builder().id(1L).title("Test Site 1").build(),
            Site.builder().id(2L).title("Test Site 2").build()
        );
        when(siteRepository.findAll()).thenReturn(expectedSites);

        // When
        List<Site> actualSites = siteService.findAll();

        // Then
        assertEquals(expectedSites.size(), actualSites.size());
        assertEquals(expectedSites, actualSites);
        verify(siteRepository, times(1)).findAll();
    }
}
```

#### Integration Tests
For integration tests:
1. Use `@SpringBootTest` annotation to load the application context
2. Use `@DataJpaTest` for repository tests with an in-memory database
3. Use `@WebMvcTest` for controller tests without loading the full application context

## Additional Development Information

### Project Structure
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

### Working with Entities and DTOs
- Use MapStruct for mapping between entities and DTOs
- Define mapping interfaces in the `mapper` package
- Use builder pattern for creating entities and DTOs

### Security
- The application uses Spring Security with JWT authentication
- JWT configuration is in the `config/jwt` package
- Security configuration is in the `config/security` package

### Database
- Use JPA repositories for database access
- Define custom query methods following Spring Data naming conventions
- Use `@Query` annotation for complex queries
- Database migrations are managed with Liquibase
