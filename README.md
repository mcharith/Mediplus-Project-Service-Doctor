# Doctor-Service

---

A dedicated microservice responsible for managing **doctors** in the Channeling Center Management system.  
It exposes a **RESTful JSON API** consumed by the API Gateway, allowing other services to create, read, update, and delete doctor records efficiently.  
The service ensures data consistency, validation, and integrates seamlessly with other microservices in the system.

---

## 👤 Student Information

- **Student Name:** Charith Mihiranga Siriwardana
- **Student Number:** 2301691075
- **Slack:** https://ijse-eca-hdse-69-70.slack.com/team/U0AHD5TQ4H5
- **GCP Project ID:** its-2130-eca-gdse-491417

---

## 📖 About

Doctor-Service is designed to handle all operations related to doctors in a healthcare or channeling environment.  
It allows the management of doctor records including ID, name, and specialization, with strict validation rules (Doctor ID format: uppercase letters).  
The service uses **Spring Boot** and **Spring Cloud** for microservice integration, while **MongoDB** stores doctor data in a scalable and flexible manner.  
With **DTO mapping via MapStruct**, **validation using Spring Validation**, and **boilerplate reduction via Lombok**, the service is maintainable, efficient, and production-ready.  
It also exposes health and monitoring endpoints via **Spring Boot Actuator**.

---

## 🛠️ Tech Stack

| Technology                       | Details                                           |
|----------------------------------|--------------------------------------------------|
| Java                              | 25                                               |
| Spring Boot                        | 4.0.3                                            |
| Spring Cloud                       | 2025.1.0                                         |
| Spring Data JPA                     | ORM / persistence layer                           |
| PostgreSQL / MongoDB                | Relational / NoSQL database (MongoDB on port 13500) |
| MapStruct                          | DTO ↔ Entity mapping                              |
| Lombok                             | Boilerplate reduction                             |
| Spring Validation                  | Bean validation                                   |
| Spring Cloud Netflix Eureka Client | Service registration & discovery                |
| Spring Cloud Config Client         | Fetches config from Config-Server               |
| Spring Boot Actuator               | Health & management endpoints                     |

---

## ⚙️ Service Details

| Property      | Value                                             |
|---------------|--------------------------------------------------|
| Port          | 8002                                             |
| Artifact ID   | doctor-service                                   |
| Group ID      | lk.ijse.eca                                     |
| Database      | MongoDB — `localhost:13500 / medi-plus`         |

---

## 📡 API Endpoints

**Base path:** `/api/v1/doctor`

| Method | Path                       | Description          | Content-Type      |
|--------|----------------------------|--------------------|-----------------|
| POST   | /api/v1/doctor             | Create a new doctor | application/json |
| GET    | /api/v1/doctor             | Get all doctors     | —               |
| GET    | /api/v1/doctor/{doctorId}  | Get a doctor by ID  | —               |
| PUT    | /api/v1/doctor/{doctorId}  | Update a doctor     | application/json |
| DELETE | /api/v1/doctor/{doctorId}  | Delete a doctor     | —               |

> **Doctor ID format:** `^[A-Z]+$` — uppercase letters only (e.g., HDSE, BSC).  
> The Doctor ID is the primary key and cannot be changed after creation.

---

## 📝 Sample Request Body

```json
{
  "doctorId": "DOC",
  "name": "Dr. Nimal",
  "specialization": "Cardiology"
}
```

---

## 🚀 Getting Started

### Prerequisites

- Config-Server, Service-Registry, and API Gateway must be running.
- A MongoDB instance must be accessible on port `13500` with credentials `root / mongodb`.

### Startup Order

1. Config-Server (9100)
2. Service-Registry (9001)
3. API Gateway (7001)
4. Doctor-Service (8002)

### Run the Service

```bash
./mvnw spring-boot:run
```

The service will start on **port 8002** and expose all endpoints listed above.

---