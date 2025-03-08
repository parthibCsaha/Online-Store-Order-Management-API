# Online Store Order Management API

A robust RESTful API built with Spring Boot, Spring Data JPA, Lombok, and PostgreSQL for managing an online store. This API handles customers, products, orders, and order items while efficiently managing complex entity relationships and ensuring data integrity.

---

## Overview

This project is designed to serve as the backend for an online store, offering full CRUD (Create, Read, Update, Delete) operations for managing:

- **Customers** – Manage customer details such as name and email.
- **Products** – Manage product listings, descriptions, pricing, and stock levels.
- **Orders** – Create and manage customer orders.
- **Order Items** – Manage individual order items associated with products.

The API ensures that operations on parent entities cascade to related child entities, and it handles bidirectional relationships with Jackson annotations to prevent infinite recursion during JSON serialization.

---

## Key Features

- **RESTful Endpoints:**  
  Fully functional endpoints for managing customers, products, orders, and order items.

- **Entity Relationships:**  
  - One-to-Many between Customer and Order  
  - One-to-Many between Order and OrderItem  
  - Many-to-One between OrderItem and Product

- **Data Integrity:**  
  Cascading operations and validation ensure consistent data management.

- **Automatic Timestamps:**  
  Utilizes Hibernate’s `@CreationTimestamp` for tracking order dates.

- **Efficient JSON Serialization:**  
  Uses Jackson annotations (`@JsonManagedReference`, `@JsonBackReference`) to manage bidirectional relationships.

- **Clean and Maintainable Code:**  
  Lombok is used extensively to reduce boilerplate and improve readability.

---

## Technologies Used

- **Java 21**
- **Spring Boot**
- **Spring Data JPA**
- **PostgreSQL**
- **Lombok**
- **Jackson**

---
