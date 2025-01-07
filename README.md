# web-connect-api

REST API for Web Application Integration

## Project Description

The `web-connect-api` project is a RESTful API designed for web application integration. It leverages Spring Boot, JPA, and PostgreSQL to provide a robust backend service.

## Technologies Used

- Java 11
- Spring Boot 2.2.7
- Spring Data JPA
- PostgreSQL
- Maven
- Jib for containerization
- JUnit for testing
- JaCoCo for code coverage
- PMD, Checkstyle, and SpotBugs for code quality

## Getting Started

### Prerequisites

- Java 11
- Maven
- PostgreSQL

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/web-connect-api.git
    cd web-connect-api
    ```

2. Configure the database in `src/main/resources/application-venu.properties`:
    ```ini
    spring.datasource.url=jdbc:postgresql://localhost:6432/develop
    spring.datasource.username=postgres
    spring.datasource.password=password
    ```

3. Build the project:
    ```sh
    mvn clean install
    ```

4. Run the application:
    ```sh
    mvn spring-boot:run
    ```

### Running Tests

To run the tests, use the following command:
```sh
mvn test