# Task & Issue Management API

A production-style RESTful Backend API built with **Java 21**, **Spring Boot**, and **PostgreSQL**, deployed with modern **DevOps & Cloud** practices (Docker, GitHub Actions CI/CD, AWS, Terraform).

---

## 🛠 Tech Stack

- **Backend:** Java 21, Spring Boot 3, Spring Data JPA, Spring Security, JWT, Flyway, OpenAPI / Swagger
- **Database:** PostgreSQL (Containerized via Docker)
- **DevOps & Cloud:** Linux, Docker, Docker Compose, AWS (IAM, VPC, EC2, S3, ECR), GitHub Actions, Terraform
- **Testing & Quality:** JUnit 5, Mockito, MockMvc, Postman

---

## 🚀 Getting Started

### Prerequisites
- JDK 21+
- Apache Maven 3.9+
- Docker & Docker Compose
- PostgreSQL 16 (or run via Docker)

### Local Database Setup (Docker)
```bash
docker run -d \
  --name taskmanager-db \
  -e POSTGRES_DB=taskmanager_db \
  -e POSTGRES_USER=taskmanager \
  -e POSTGRES_PASSWORD=taskmanager123 \
  -p 5432:5432 \
  -v taskmanager-pgdata:/var/lib/postgresql/data \
  postgres:16-alpine
```

---

## 📖 Roadmap & Progress

- [x] Day 0: Environment Setup & Tooling
- [ ] Day 1: Schema Design, JPA Entities & Task CRUD
- [ ] Day 2: REST Standards, DTOs, Validation & Exception Handling
- [ ] Day 3: Spring Security Filter Chain & RBAC
- [ ] Day 4: JWT Authentication & Dockerization
- [ ] Day 5: Unit/Integration Testing & GitHub Actions CI
- [ ] Day 6: Flyway Migrations, OpenAPI Docs & AWS EC2 Deploy
- [ ] Day 7: End-to-End Pipeline & Portfolio Polish
