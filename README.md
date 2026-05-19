# Quote Generator Backend

Quote Generator Backend is a RESTful API built with Spring Boot.  
It provides quote management features such as creating, reading, updating, deleting, searching, and filtering quotes by category.

This backend is designed to work with the Quote Generator Frontend built with React.

## Features

- Get all quotes
- Get a quote by ID
- Create a new quote
- Update an existing quote
- Delete a quote
- Search quotes by keyword
- Filter quotes by category
- PostgreSQL database integration
- JPA/Hibernate entity mapping
- Input validation
- Global exception handling
- Custom error response structure
- Initial database setup using `schema.sql` and `data.sql`
- CORS support for frontend development

## Tech Stack

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- PostgreSQL
- Lombok
- Jakarta Validation

## Project Structure

```txt
src/main/java/com/amber/quotegeneratorbackend/
├── controller/
│   └── QuoteController.java
│
├── dto/
│   ├── QuoteRequest.java
│   └── QuoteResponse.java
│
├── entity/
│   └── Quote.java
│
├── exception/
│   ├── ErrorResponse.java
│   ├── GlobalExceptionHandler.java
│   └── ResourceNotFoundException.java
│
├── mapper/
│   └── QuoteMapper.java
│
├── repo/
│   └── QuoteRepository.java
│
├── service/
│   └── QuoteService.java
│
└── QuotegeneratorbackendApplication.java
```

```txt
src/main/resources/
├── application.properties
├── schema.sql
└── data.sql
```

## API Endpoints

Base URL:

```txt
http://localhost:8080/api/v1/quotes
```

## Quote Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/v1/quotes` | Get all quotes |
| GET | `/api/v1/quotes/{id}` | Get a quote by ID |
| POST | `/api/v1/quotes` | Create a new quote |
| PUT | `/api/v1/quotes/{id}` | Update a quote |
| DELETE | `/api/v1/quotes/{id}` | Delete a quote |
| GET | `/api/v1/quotes/search?keyword={keyword}` | Search quotes by keyword |
| GET | `/api/v1/quotes/category/{category}` | Get quotes by category |

## Quote Entity

Each quote contains the following fields:

```java
private Long id;
private String content;
private String author;
private String category;
private LocalDateTime createdAt;
private LocalDateTime updatedAt;
```

## Validation

The backend validates required fields before saving quote data.

Example validation rules:

```java
@NotBlank(message = "Content is required")
private String content;

@NotBlank(message = "Author is required")
private String author;
```

If required data is missing, the backend returns an error response instead of saving invalid data.

## Error Handling

The project uses global exception handling to provide consistent error responses.

Example error response:

```json
{
  "status": 404,
  "message": "Quote not found with id: 1",
  "timestamp": "2026-05-19T15:00:00"
}
```

Common handled errors include:

- Quote not found
- Validation failure
- General server error

## Database Configuration

The project uses PostgreSQL.

Example `application.properties`:

```properties
spring.application.name=quotegeneratorbackend

spring.datasource.url=jdbc:postgresql://localhost:5432/quote_db
spring.datasource.username=postgres
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=none
spring.sql.init.mode=always

spring.jpa.show-sql=true
```

> Note: Replace `your_password` with your own local PostgreSQL password.

## Database Initialization

The database structure is defined in:

```txt
schema.sql
```

Initial quote data is inserted through:

```txt
data.sql
```

This allows the project to start with sample quote data for testing and development.

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/lixinjie520/QuoteGeneratorBackend.git
cd QuoteGeneratorBackend
```

### 2. Create PostgreSQL database

Create a local PostgreSQL database:

```sql
CREATE DATABASE quote_db;
```

### 3. Update database settings

Update your local database username and password in:

```txt
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.username=postgres
spring.datasource.password=your_password
```

### 4. Run the project

You can run the project from IntelliJ IDEA or use:

```bash
./mvnw spring-boot:run
```

For Windows:

```bash
mvnw.cmd spring-boot:run
```

The backend will usually run at:

```txt
http://localhost:8080
```

## Example Request

### Create a quote

```http
POST /api/v1/quotes
Content-Type: application/json
```

Request body:

```json
{
  "content": "If you never try, you will never know.",
  "author": "Unknown",
  "category": "Motivation"
}
```

### Example Response

```json
{
  "id": 1,
  "content": "If you never try, you will never know.",
  "author": "Unknown",
  "category": "Motivation",
  "createdAt": "2026-05-19T15:00:00",
  "updatedAt": "2026-05-19T15:00:00"
}
```

## Frontend Integration

This backend is designed to work with the Quote Generator Frontend.

Frontend development URL:

```txt
http://localhost:5173
```

CORS is configured to allow frontend requests during local development.

## Future Improvements

- Add pagination
- Add authentication and authorization
- Add user accounts
- Add favorite quotes
- Add quote tags
- Add advanced search
- Add DTOs for cleaner API responses
- Add unit and integration tests
- Deploy backend to a cloud platform
- Move database from local PostgreSQL to a cloud database

## Author

Created by Amber Wang as a full-stack web development practice project.
