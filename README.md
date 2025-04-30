# 🧾 MiNegocio - Gestión de Clientes

Este proyecto es una API REST desarrollada con **Java 21**, **Spring Boot**, y **PostgreSQL**, que permite gestionar clientes y sus direcciones, cumpliendo con principios SOLID, uso de DTOs, Mappers, y una arquitectura lista para escalar.

## 🧱 Tecnologías utilizadas

- Java 21
- Spring Boot 3.4.5
- PostgreSQL (Docker)
- JPA/Hibernate
- MapStruct
- Lombok
- Swagger/OpenAPI 3
- Maven 3.9.5
- JUnit 5 y Mockito para testing

---

## 📐 Arquitectura del proyecto

La estructura del proyecto sigue una **arquitectura por capas**, aplicando principios SOLID y separación de responsabilidades:

```
com.minegocio.alquimiasoft
├── controller        # Expone los endpoints REST
├── dto              # Objetos de transferencia de datos (Request/Response)
├── entity           # Entidades JPA (persistencia en BD)
├── mapper           # Conversión entre Entity y DTO usando MapStruct
├── repository       # Acceso a datos con Spring Data JPA
├── service          # Contiene la lógica de negocio
├── exception        # Manejo centralizado de errores
```

### ✅ Uso de buenas prácticas

- **DTOs:** para no exponer las entidades directamente en la capa de presentación.
- **Mappers:** uso de MapStruct para mapear entre Entity y DTO de manera limpia y eficiente.
- **Repositorio:** uso de Spring Data JPA con interfaces limpias y extendibles.
- **Servicios:** lógica de negocio encapsulada, separada de la capa de control.
- **Swagger:** documentación automática de los endpoints REST.
- **Tests:** pruebas unitarias con JUnit y Mockito (mockeo de dependencias).

---

## 🚀 Endpoints disponibles

Una vez corriendo la aplicación, puedes consultar y probar todos los endpoints en:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 🐘 Base de datos con Docker

Puedes levantar la base de datos PostgreSQL usando:

```bash
docker run --name alquimiasoft_db   -e POSTGRES_PASSWORD=postgres   -e POSTGRES_USER=postgres   -e POSTGRES_DB=alquimiasoft_db   -p 5432:5432   -d postgres
```

### Configuración en `application.properties`:

```properties
spring.datasource.platform=postgres
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.url=jdbc:postgresql://localhost:5432/alquimiasoft_db
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.show-sql=false
spring.jpa.open-in-view=false
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.format_sql=false
```

---

## ⚙️ Cómo ejecutar el proyecto

### Opción 1: Desde Maven

```bash
mvn clean install
mvn spring-boot:run
```

### Opción 2: Desde el JAR

```bash
mvn clean package
java -jar target/alquimiasoft-0.0.1-SNAPSHOT.jar
```

---

## 🧪 Pruebas

Ejecuta los tests con:

```bash
mvn test
```

Incluye pruebas unitarias de servicios usando **Mockito**

---

## 📄 Swagger y JSON de documentación

- **URL del Swagger UI:**  
  http://localhost:8080/swagger-ui/index.html

- **Documentación OpenAPI en JSON:**  
  http://localhost:8080/v3/api-docs

---


## 👨‍💻 Autor

Este proyecto fue desarrollado por **Ariel Vazquez** como parte de una prueba técnica, aplicando buenas prácticas modernas de desarrollo en Java y Spring Boot.