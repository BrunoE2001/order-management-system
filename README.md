# order-management-system
**Proyecto:**
    Sistema de gestión de pedidos (Order Management System)

**Arquitectura:**
    Hexagonal (ports and adapters) + Clean Architecture

**Patrones de diseño:**
* Factory
* Strategy
* Repository
* DTO
* Builder
* Facade
* Specification
* Dependency Injection (propio de Spring)

---
**Idea del sistema**
Sistema simple de pedidos de una tienda.

**Funcionalidades:**
* Crear un pedido
* Agregar productos
* Calcular precio
* Aplicar diferentes estrategias de descuento
* Cambiar el estado del pedido

ENDPOINTS:

    Ejemplo:
    POST /orders
    POST /orders/{id}/items
    POST /orders/{id}/checkout
    POST /orders/{id}/