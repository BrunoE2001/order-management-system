# order-management-system

Sistema de gestión de pedidos desarrollado con **Spring Boot** siguiendo principios de **arquitectura hexagonal** y **Clean Architecture**.

Este proyecto está pensado como práctica de **arquitectura de software y patrones de diseño** en aplicaciones backend.

---
# Arquitectura

El proyecto utiliza:

- Hexagonal Architecture (Ports & Adapters)
- Clean Architecture

Separando responsabilidades en capas:
* api → controllers
* application → servicios / casos de uso
* domain → entidades y lógica de negocio
* infrastructure → repositorios / acceso a datos
---

---

# Patrones de diseño utilizados

- Factory
- Strategy
- Repository
- DTO
- Builder
- Facade
- Specification
- Dependency Injection (Spring)

---

# Tecnologías utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- H2 (tests)
- MySQL (desarrollo)
- Docker
- Maven
- JUnit 5
- MockMvc
- MapStruct
- Lombok

---

# Funcionalidades

El sistema permite:

- Crear pedidos
- Agregar productos a pedidos
- Calcular el total del pedido
- Aplicar estrategias de descuento
- Consultar pedidos
- Filtrar productos usando Specification Pattern

---

# Endpoints principales

### Crear pedido
    POST /orders
### Agregar item a un pedido
    POST /orders/{id}/items
### Checkout del pedido
    POST /orders/{id}/checkout
### Obtener pedidos
    GET /orders
### Filtrar pedidos
    POST /orders/{id}/
### Obtener productos
    GET /products


---

# Ejemplo de request
    POST /orders
```json
{
    "discountType": "VIP",
    "items": [{
        "productName": "Laptop",
        "price": 1000,
        "quantity": 1
    }]
}
```
---
# Cómo ejecutar el proyecto

1. Clonar el repositorio
    ```git clone https://github.com/BrunoE2001/order-management-system.git```

2. Ejecutar base de datos con Docker
   ```docker compose up -d```

3. Ejecutar la aplicación
    ```mvn spring-boot:run```

---
# Configuración de base de datos
### Desarrollo
MySQL ejecutándose en Docker
```jdbc:mysql://localhost:3306/order_management```

### Tests
Base en memoria H2
Configurada en:
    application-test.yml

### Ejecutar tests
    mvn test

# Estructura del proyecto
```
src
├── api
│ └── controller
│
├── application
│ ├── dto
│ ├── mapper
│ └── service
│
├── domain
│ ├── model
│ ├── repository
│ └── strategy
│
├── infrastructure
│ ├── repository
│ └── specification
```

---
# Mejores prácticas aplicadas

* Clean Code
* Separation of Concerns
* SOLID Principles
* Hexagonal Architecture
* Testable Design

---
# Mejoras futuras

* Implementar eventos de dominio
* Agregar autenticación JWT
* Integrar Testcontainers
* Versionado de base de datos con Flyway
* Documentación con OpenAPI / Swagger