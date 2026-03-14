# Java DDD CRUD Clientes

API REST desarrollada con **Spring Boot** siguiendo los principios de **Domain-Driven Design (DDD)**. Gestiona las entidades `Producto` y `Cliente` con operaciones CRUD completas.

---

## Tecnologías

- Java 18
- Spring Boot 3.2.3
- Spring Data JPA
- PostgreSQL
- Flyway (migraciones)
- MapStruct (mapeo entre capas)
- Lombok
- Maven

---

## Arquitectura DDD

```
src/main/java/com/example/productosapi/
├── application/
│   └── usecase/              # Casos de uso (Create, Get, Update, Delete)
├── domain/
│   ├── event/                # Eventos de dominio
│   ├── exception/            # Excepciones de negocio
│   ├── model/                # Entidades de dominio (inmutables)
│   ├── repository/           # Interfaces de repositorio
│   └── service/              # Servicios de dominio
└── infrastructure/
    ├── config/               # Configuración JPA / base de datos
    ├── persistence/
    │   ├── entity/           # Entidades JPA
    │   ├── mapper/           # MapStruct mappers
    │   └── repository/       # Adaptadores de repositorio
    └── rest/
        ├── controller/       # Controladores REST
        └── dto/              # Request / Response DTOs
```

---

## Requisitos previos

- Java 18+
- Maven 3.8+
- PostgreSQL instalado y en ejecución

---

## Pasos para ejecutar localmente

### 1. Clonar el repositorio

```bash
git clone https://github.com/qbitronx/java-ddd-crud-clientes.git
cd java-ddd-crud-clientes
```

### 2. Crear la base de datos

Conectarse a PostgreSQL y ejecutar:

```sql
CREATE DATABASE "arqsw-db08";
```

### 3. Configurar credenciales

Editar `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/arqsw-db08
spring.datasource.username=postgres
spring.datasource.password=tu_password
```

### 4. Compilar el proyecto

```bash
mvn clean package -DskipTests
```

### 5. Ejecutar la aplicación

```bash
mvn spring-boot:run
```

> Al iniciar, **Flyway** aplicará automáticamente las migraciones SQL y creará las tablas `productos` y `clientes` en la base de datos.

### 6. Verificar que está corriendo

```bash
curl http://localhost:8000/api/clientes
curl http://localhost:8000/api/productos
```

La aplicación queda disponible en: `http://localhost:8000`

---

## Endpoints - Clientes

| Método | URL | Descripción |
|--------|-----|-------------|
| `POST` | `/api/clientes` | Crear cliente |
| `GET` | `/api/clientes` | Listar todos los clientes |
| `GET` | `/api/clientes/{id}` | Obtener cliente por ID |
| `GET` | `/api/clientes/codigo/{codigo}` | Obtener cliente por código |
| `PUT` | `/api/clientes/{id}` | Actualizar datos del cliente |
| `PATCH` | `/api/clientes/{id}/estado` | Activar / desactivar cliente |
| `DELETE` | `/api/clientes/{id}` | Eliminar cliente |

### Ejemplo - Crear cliente

```bash
curl -X POST http://localhost:8000/api/clientes \
  -H "Content-Type: application/json" \
  -d '{
    "codigo": "CLI-001",
    "tipoDocIdentidad": "RUC",
    "nroDocIdentidad": "20100182282",
    "razonSocial": "Empresa Ejemplo S.A.C.",
    "direccion": "Av. Javier Prado 1234, Lima"
  }'
```

### Ejemplo - Actualizar estado

```bash
curl -X PATCH http://localhost:8000/api/clientes/{id}/estado \
  -H "Content-Type: application/json" \
  -d '{"activo": false}'
```

---

## Endpoints - Productos

| Método | URL | Descripción |
|--------|-----|-------------|
| `POST` | `/api/productos` | Crear producto |
| `GET` | `/api/productos` | Listar todos los productos |
| `GET` | `/api/productos/{id}` | Obtener producto por ID |
| `GET` | `/api/productos/codigo/{codigo}` | Obtener producto por código |
| `GET` | `/api/productos/categoria/{categoria}` | Listar por categoría |
| `PUT` | `/api/productos/{id}` | Actualizar producto |
| `PATCH` | `/api/productos/{id}/estado` | Activar / desactivar producto |
| `PATCH` | `/api/productos/{id}/stock` | Actualizar stock |
| `DELETE` | `/api/productos/{id}` | Eliminar producto |

---

## Migraciones Flyway

Las migraciones se aplican automáticamente al iniciar la aplicación:

| Versión | Archivo | Descripción |
|---------|---------|-------------|
| V1 | *(baseline)* | Tabla `productos` (pre-existente) |
| V2 | `V2__create_clientes_table.sql` | Crear tabla `clientes` |
| V3 | `V3__alter_clientes_tipo_doc_to_varchar.sql` | Ajuste de tipo de columna |
