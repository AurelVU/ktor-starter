# ktor-starter

A minimal yet well-structured Ktor REST API template featuring OpenAPI/Swagger documentation, Koin dependency injection, and clean feature-based architecture. Ideal for students and as a starting point for new projects.

## Tech Stack

- **Ktor** - Kotlin async web framework
- **Koin** - Lightweight dependency injection
- **OpenAPI/Swagger UI** - Auto-generated API documentation
- **Kotlin Serialization** - JSON serialization
- **Netty** - High-performance server engine
- **Java 21**

## Project Structure

```
app/src/main/kotlin/org/example/
├── Application.kt           # Entry point
├── plugins/                  # Ktor plugins configuration
│   ├── CORS.kt
│   ├── Koin.kt
│   ├── OpenAPI.kt
│   ├── Routing.kt
│   ├── Serialization.kt
│   └── StatusPages.kt
├── di/                       # Dependency injection
│   └── AppModule.kt
├── common/                   # Shared components
│   └── dto/
└── features/                 # Feature modules
    ├── health/
    │   └── presentation/
    └── users/
        ├── domain/
        │   ├── model/
        │   └── repository/
        ├── data/
        └── presentation/
            └── dto/
```

## Getting Started

### Prerequisites

- JDK 21+
- Gradle 8.x

### Run

```bash
./gradlew run
```

The server starts at `http://localhost:8080`

### API Documentation

Swagger UI is available at: `http://localhost:8080/swagger`

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/health` | Health check |
| GET | `/api/users` | Get all users |
| GET | `/api/users/{id}` | Get user by ID |
| POST | `/api/users` | Create user |
| PUT | `/api/users/{id}` | Update user |
| DELETE | `/api/users/{id}` | Delete user |

## License

MIT
