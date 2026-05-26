# **OrderFlow Spring API**

A **Spring Boot REST API** designed to manage **clients**, **products**, **orders**, and **order lines**.

This project provides a backend system for basic commercial order management. It allows users or applications to create clients, manage products, create orders, assign orders to clients, and add products to orders through order lines.

The application can be used as a backend foundation for:

- **Order management systems**
- **Client management applications**
- **Product catalog APIs**
- **Sales management platforms**
- **Small e-commerce backends**
- **ERP learning projects**
- **Spring Boot CRUD practice projects**

---

# **Project Purpose**

The goal of this project is to build a clean and simple **RESTful backend** using **Spring Boot**, **Spring Data JPA**, and **MySQL**.

The backend manages the following business logic:

- A **client** can have multiple orders.
- An **order** belongs to one client.
- An **order** can contain multiple products.
- A **product** can appear in multiple order lines.
- A **line order** links a product to an order with a specific quantity.

---

# **Technologies Used**

## **Backend**

- **Java 17**
- **Spring Boot 3**
- **Spring Web**
- **Spring Data JPA**
- **Hibernate**
- **Maven**
- **Lombok**

## **Database**

- **MySQL**
- **H2 Database**

## **Testing**

- **JUnit 5**
- **Mockito**
- **Spring Boot Test**

---

# **Main Features**

- **Client CRUD operations**
- **Product CRUD operations**
- **Order CRUD operations**
- **Add an order to a specific client**
- **Add a product to an existing order**
- **Manage order lines with quantities**
- **REST API architecture**
- **JPA entity relationships**
- **MySQL database integration**
- **Unit testing with JUnit and Mockito**

---

# **Project Structure**

```bash
backend_spring/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/demo/
│   │   │       ├── controller/
│   │   │       │   ├── ClientController.java
│   │   │       │   ├── CommandeController.java
│   │   │       │   └── ProductController.java
│   │   │       │
│   │   │       ├── entity/
│   │   │       │   ├── Client.java
│   │   │       │   ├── Commande.java
│   │   │       │   ├── LigneCommande.java
│   │   │       │   └── Product.java
│   │   │       │
│   │   │       ├── repository/
│   │   │       ├── service/
│   │   │       └── DemoApplication.java
│   │   │
│   │   └── resources/
│   │       └── application.properties
│   │
│   └── test/
│       └── java/com/example/demo/
│           ├── ClientControllerTest.java
│           └── ClientServiceTest.java
│
├── pom.xml
└── README.md
```

---

# **Main Entities**

## **Client**

Represents a customer.

Main fields:

- **id**
- **nom**
- **prenom**
- **email**
- **telephone**

---

## **Product**

Represents a product that can be added to an order.

Main fields:

- **id**
- **name**
- **description**
- **price**

---

## **Commande**

Represents a customer order.

Main fields:

- **idCommande**
- **dateCommande**
- **etat**
- **client**

---

## **LigneCommande**

Represents an order line linking a product to an order.

Main fields:

- **idLigneCommande**
- **commande**
- **product**
- **quantite**

---

# **API Endpoints**

## **Client Endpoints**

| Method | Endpoint | Description |
|---|---|---|
| **GET** | `/api/clients` | Get all clients |
| **GET** | `/api/clients/{id}` | Get client by ID |
| **POST** | `/api/clients` | Create a new client |
| **PUT** | `/api/clients/{id}` | Update an existing client |
| **DELETE** | `/api/clients/{id}` | Delete a client |
| **POST** | `/api/clients/{clientId}/commandes` | Add an order to a specific client |

---

## **Product Endpoints**

| Method | Endpoint | Description |
|---|---|---|
| **GET** | `/api/products` | Get all products |
| **GET** | `/api/products/{id}` | Get product by ID |
| **POST** | `/api/products` | Create a new product |
| **PUT** | `/api/products/{id}` | Update an existing product |
| **DELETE** | `/api/products/{id}` | Delete a product |

---

## **Commande Endpoints**

| Method | Endpoint | Description |
|---|---|---|
| **GET** | `/api/commandes` | Get all orders |
| **GET** | `/api/commandes/{id}` | Get order by ID |
| **POST** | `/api/commandes` | Create a new order |
| **PUT** | `/api/commandes/{id}` | Update an existing order |
| **DELETE** | `/api/commandes/{id}` | Delete an order |
| **POST** | `/api/commandes/{commandeId}/ligneCommande` | Add a product to an order |
| **POST** | `/api/commandes/client/{clientId}` | Add an order to a specific client |

---

# **Database Configuration**

The application is configured to use **MySQL**.

Default configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/springbootdb
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true
```

---

# **Installation**

## **1. Clone the repository**

```bash
git clone https://github.com/Noris69/backend_spring.git
cd backend_spring
```

---

## **2. Create the MySQL database**

Open MySQL and create the database:

```sql
CREATE DATABASE springbootdb;
```

---

## **3. Configure database credentials**

Open:

```bash
src/main/resources/application.properties
```

Update your MySQL username and password if needed:

```properties
spring.datasource.username=root
spring.datasource.password=root
```

---

## **4. Install dependencies**

```bash
mvn clean install
```

---

## **5. Run the application**

```bash
mvn spring-boot:run
```

The backend will run on:

```bash
http://localhost:8080
```

---

# **Example Requests**

## **Create a Client**

```http
POST /api/clients
Content-Type: application/json
```

```json
{
  "nom": "Dupont",
  "prenom": "Jean",
  "email": "jean.dupont@example.com",
  "telephone": "0600000000"
}
```

---

## **Create a Product**

```http
POST /api/products
Content-Type: application/json
```

```json
{
  "name": "Laptop",
  "description": "Business laptop",
  "price": 8500.0
}
```

---

## **Create a Commande**

```http
POST /api/commandes
Content-Type: application/json
```

```json
{
  "dateCommande": "2025-01-10",
  "etat": "CREATED"
}
```

---

## **Add a Product to an Order**

```http
POST /api/commandes/1/ligneCommande?productId=1&quantity=2
```

---

# **Useful Commands**

## **Build the project**

```bash
mvn clean install
```

## **Run the project**

```bash
mvn spring-boot:run
```

## **Run tests**

```bash
mvn test
```

## **Clean generated files**

```bash
mvn clean
```

---

# **Testing**

The project includes unit tests for:

- **ClientController**
- **ClientService**

Testing is based on:

- **JUnit 5**
- **Mockito**
- **MockMvc**

---

# **Git Ignore Recommendations**

Add the following to `.gitignore`:

```gitignore
target/
*.log
.idea/
.vscode/
.env
.DS_Store
```

---

# **Future Improvements**

- **Add DTOs for request and response models**
- **Add exception handling with @ControllerAdvice**
- **Add validation with Bean Validation**
- **Improve HTTP status responses**
- **Add Swagger/OpenAPI documentation**
- **Add pagination for clients, products, and orders**
- **Add authentication and authorization**
- **Add Docker support**
- **Add integration tests**
- **Replace ddl-auto=create with update or validate in production**

---

# **Author**

Developed by **Noris69**.
