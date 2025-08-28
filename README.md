# 🏥 Hospital Management System (Microservices)

## 📌 Overview

Hệ thống quản lý bệnh viện được xây dựng theo kiến trúc **Microservices**, mục tiêu là:

- Quản lý tài khoản, bệnh nhân, nhân viên, lịch khám, thuốc, thông báo và thống kê.
- Hỗ trợ cả **REST synchronous** và **event-driven asynchronous** communication.

---

## 🛠️ Tech Stack

- **Ngôn ngữ:** Java (Spring Boot).
- **Frameworks & Libraries:**
  - Spring Boot, Spring Cloud (Eureka, Gateway, OpenFeig)
  - Eureka Server làm service registry
  - Spring Security + JWT
  - Resilience4j (Circuit Breaker, Retry, Rate Limiter)
- **Messaging:** RabbitMQ
- **Databases:**
  - MySQL
  - Redis

---

## 🧩 Microservices

| Service                          | Description                                            |
| -------------------------------- | ------------------------------------------------------ |
| **Accounts Service**             | Quản lý account, phân quyền                            |
| **Auth Service**                 | Quản lý token khi login                                |
| **Patients Service**             | Quản lý hồ sơ bệnh nhân, tài khoản bệnh nhân           |
| **Employees Service**            | Quản lý nhân viên, bác sĩ, dược sĩ, tiếp tân           |
| **Appointments Service**         | Quản lý lịch hẹn, phiếu khám, kết quả chỉ định dịch vụ |
| **Medication Service**           | Quản lý thuốc, đơn thuốc, chi tiết đơn thuốc           |
| **Notifications Service**        | Gửi email (SMTP) thông báo lịch khám, đơn thuốc        |
| **Reporting/Statistics Service** | Báo cáo & thống kê                                     |

---

## 🔗 Communication

- **Synchronous (REST):** Feign Client + API Gateway.
- **Asynchronous (Event-driven):** RabbitMQ (fanout, topic exchanges).
- **Event DTOs:** `AccountCreatedEvent`, `AppointmentCreatedEvent`, `PrescriptionReadyEvent`, …

---

## 🔒 Security

- **JWT Authentication & Authorization**.
- **Gateway Filter**: validate token trước khi request vào microservice.
- **Giao tiếp giữa services**: Forward authentication qua interceptor

---
