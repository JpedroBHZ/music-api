# Music & Setlist Manager API 🎵🎸

A robust, production-ready Spring Boot RESTful API designed for managing music catalogs and organizing concert setlists. 

This project incorporates industry-standard development practices, including layered architecture, data isolation via DTOs, a relational database, global exception handling, custom validations, token-based dynamic security, automated unit testing, and a continuous integration pipeline (CI/CD).

---

## 🛠️ Tech Stack & Ecosystem

* **Core Language:** Java 17+
* **Framework:** Spring Boot 3+
* **Data Persistence:** Spring Data JPA & Hibernate
* **Database:** PostgreSQL (Production/Dev)
* **Security:** Spring Security & JWT (JSON Web Token) *[In Development]*
* **Data Validation:** Jakarta Bean Validation (Hibernate Validator)
* **Testing & Quality:** JUnit 5 & Mockito *[In Development]*
* **Containerization:** Docker & Docker Compose
* **Development Environment:** WSL 2 (Ubuntu Linux) integrated with IntelliJ IDEA
* **API Documentation:** Swagger UI / OpenAPI 3
* **DevOps / Automation:** GitHub Actions CI/CD *[In Development]*

---

## 📐 Architecture & Design Patterns

The project strictly follows the architectural patterns required by the enterprise market:

* **Layered Architecture:** Clear decoupling between `Controller` (HTTP Endpoints), `Service` (Business Logic), and `Repository` (Database Access).
* **DTO Pattern (Data Transfer Object):** Total isolation of database entities (`Model`). The API uses `RequestDTO` to capture and validate user input and `ResponseDTO` to format API outputs, preventing unnecessary data exposure.
* **Global Exception Handling:** Centralized through a `@ControllerAdvice` class, catching data validation failures (`MethodArgumentNotValidException`) and custom business exceptions to return clean, standardized HTTP error payloads.
* **Complex Data Mapping:** Hands-on implementation of efficient relational associations, such as the `@ManyToMany` relationship between Songs (`Song`) and Setlists (`Setlist`).

---

## 🗺️ Development Roadmap

- [x] **Phase 1:** Basic Song CRUD, DTO isolation, and data validation with Bean Validation.
- [x] **Phase 2:** Global exception handling and interactive API documentation with Swagger.
- [x] **Phase 3:** Setlist domain implementation featuring `@ManyToMany` relationships and data pagination (`Pageable`).
- [x] **Phase 4:** Database migration from an in-memory H2 database to a real **PostgreSQL** instance via **Docker Compose** inside a **WSL/Linux** environment.
- [ ] **Phase 5 (Current):** Event domain modeling (`Event`) and Cascade deletion logic.
- [ ] **Phase 6:** Ecosystem hardening with **Spring Security and JWT**.
- [ ] **Phase 7:** Automated code coverage using unit tests with **JUnit 5 and Mockito**.
- [ ] **Phase 8:** Containerizing the Java application and building a **GitHub Actions CI/CD** pipeline.

---

## 🚀 Getting Started

### Prerequisites
* Java 17 or higher installed.
* Docker and Docker Compose installed (preferably inside a WSL2/Linux environment).
* Maven (or use the included `./mvnw` wrapper).

### 1. Clone the Repository
```bash
git clone [https://github.com/JpedroBHZ/music-api.git](https://github.com/JpedroBHZ/music-api.git)
cd music-api
