# Spring Simple Point of Sale (POS) System

## Overview
This is a simple Point of Sale (POS) system built using the **Spring Framework**. It allows users to manage sales, customers, products, and inventory efficiently.

## Features
- **Item Management**: Track stock levels and restock item..
- **Customer Management**: Add and manage customer information.
- **Orders**: Generate order change stock of item.
## Technologies Used
- **Spring Boot**: For creating the REST API and application management.
- **Spring Data JPA**: For database access and management.
- **MySQL**: As the database to store POS system data.
- **Thymeleaf**: For rendering the UI (Optional if it's a web application).
- **Maven**: For project build and dependency management.
- **Lombok**: To reduce boilerplate code (annotations for getters/setters, etc.).

## Requirements
- **Java 17** or later
- **Maven 3.6+**
- **MySQL 8+**
- **IDE**: IntelliJ IDEA, Eclipse, or any Java IDE
- **Postman**: (Optional) For testing the API endpoints.

## Installation and Setup

### Clone the Repository
```bash
git https://github.com/Vihanga-Gaganatharu/POS-Sring-master.git
```



<h2>API Endpoints</h2>

<h3>Customer Endpoints</h3>

* GET /customer: Retrieve all customers.
* POST /customer: Create a new customer.
* PUT /customer: Update an existing customer.
* DELETE /customer/{id}: Delete a customer by ID.

<h3>Item Endpoints</h3>

* GET /item: Retrieve all items.
* POST /item: Create a new item.
* PUT /item: Update an existing item.
* DELETE /item/{id}: Delete an item by ID.

<h3>Order Endpoints</h3>

* POST /order: Create a new order.

<h2>💻 Built with</h2>

Technologies used in the project:

*   Spring- Backend framework in java
*   Hibernate- ORM for database interaction
*   Spring Data JPA- Data repository layer
*   MySQL- Database
*   Lombok- To reduce boilerplate code

<h2> API Documentation </h2>

* You can view the detailed API documentation with example requests and responses <a href="https://documenter.getpostman.com/view/35385296/2sAXxV7Ayv">here</a>.
