
# Spring Expense Management

This project is a learning playground built using the Spring Framework to explore different modules and architectural patterns. It includes a basic **expense management** system with additional features like integration with **News API** and **Twitter API**, designed for hands-on Spring ecosystem mastery.

---

## 🔍 Project Overview

This app demonstrates:

- Managing expenses and users
- Displaying live news via an external News API
- Optional Twitter feed integration
- Exploration of advanced Spring Boot concepts in a layered architecture

---

## 🧱 Architecture (Domain Driven Design)

- **Controller Layer** (`presentation.rest`): Exposes REST APIs
- **Application Layer** (`application`): Handles service-level logic
- **Domain Layer** (`domain.model`): Contains core models/entities
- **Infrastructure Layer** (`infrastructure`): Reserved for persistence or third-party integration

---

## 🚀 Spring Modules & Concepts Used

| Area                  | Tools/Tech Used                                           |
|-----------------------|-----------------------------------------------------------|
| Dependency Injection  | Spring Core / ApplicationContext                           |
| REST APIs             | Spring MVC, `@RestController`, `@RequestMapping`           |
| Retry Mechanism       | Spring Retry (`@Retryable`, `Backoff`)                     |
| Scheduling            | `@Scheduled` for periodic tasks                            |
| Async Processing      | `@Async` and `ExecutorService`                             |
| Persistence Layer     | Spring Data JPA                                            |
| Reactive Programming  | Spring WebFlux (`Mono`, `Flux`) - optional future integration |
| API Security          | Spring Security (Basic Auth, JWT placeholder)             |
| DB Migration          | Flyway                          |
| Datasource Management | HikariCP                                                   |
| Logging               | SLF4J                                                      |

---

## 📂 Current Controllers

- `ExpenseController`: APIs for managing expenses
- `UserController`: User registration/login (JWT planned)
- `NewsController`: Fetches latest news from News API
- `GroupController`: For managing user groups/categories
- `SpringApplicationController`: Base/root endpoint
- `DemoMvnController`: Dev/testing controller

---

## 🛠 How to Run

> This project does **not** use Docker. Just run directly via IDE or terminal.

# Kafka + ELK Pipeline Setup

To run this project, use the following Docker Compose files to set up the Kafka and ELK stack:

```bash

# Start Kafka and ELK services
docker-compose -f docker-compose.kafka.yml -f docker-compose.elk.yml up -d 
# Stop Kafka and ELK services
docker-compose -f docker-compose.kafka.yml -f docker-compose.elk.yml down
```





