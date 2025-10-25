# Proyecto Final – Análisis II  
**Carrito de Compras con Spring Boot y React**

## 📌 Descripción
Este proyecto consiste en la migración de una tienda en línea (carrito de compras) desde PHP 7.0 a **Java EE usando Spring Boot**, implementando una **API RESTful** para la gestión de productos y un carrito de compras persistente en base de datos. El frontend está desarrollado en **React** y se comunica con el backend mediante endpoints REST.

El sistema permite:
- Ver el catálogo de productos
- Agregar, eliminar y actualizar productos en el carrito
- Finalizar la compra usando diferentes métodos de pago (efectivo, tarjeta de crédito, PayPal)
- Persistencia del carrito en la base de datos (no se pierde al recargar)

## 🛠️ Tecnologías utilizadas
### Backend
- **Framework**: Spring Boot 3.5.6
- **Lenguaje**: Java 17
- **Base de datos**: MySQL 8.0
- **Patrones de diseño**:
  - **Estrategia**: Gestión de métodos de pago
  - **Factory Method**: Control de permisos de base de datos (lectura/escritura)
- **Arquitectura**: MVC en 6 capas (Controller → Service → Repository → Entity + Manejo de excepciones + Configuración)
- **Pruebas**: JUnit 5 + Mockito

### Frontend
- **Framework**: React (Vite)
- **Estilos**: Bootstrap
- **Gestión de estado**: `useReducer` + `useContext` (hooks personalizados)

## 🗂️ Estructura del proyecto


├── 5-cart-app/              # Frontend en React
├── backend-cartapp/         # Backend en Spring Boot
├── README.md

## ▶️ Cómo ejecutar el proyecto

### Requisitos
- JDK 17+
- Maven
- Node.js 18+
- MySQL 8.0+

### Backend (Spring Boot)
1. Crea la base de datos en MySQL:
   ```sql
   CREATE DATABASE db_cart_springboot_dev;

   🌐 Cómo ejecutar el proyecto 
Productos 

    GET /products → Listar todos los productos
    POST /products → Crear un nuevo producto
    PUT /products/{id} → Actualizar un producto
    DELETE /products/{id} → Eliminar un producto
     

Carrito de compras 

    GET /cart → Obtener el carrito del usuario (default_user)
    POST /cart/add?productId=1&quantity=2 → Agregar producto al carrito
    PUT /cart/item/{itemId}?quantity=3 → Actualizar cantidad
    DELETE /cart/item/{itemId} → Eliminar ítem del carrito
    DELETE /cart/clear → Vaciar carrito
    POST /cart/checkout?paymentMethod=efectivo → Finalizar compra
     

Métodos de pago soportados 

    efectivo
    tarjetacredito
    paypal
     
