# Proyecto Universidad

Sistema de gestión universitaria desarrollado en **Java**, utilizando una arquitectura por capas, interfaz gráfica con **Java Swing**, persistencia mediante **JPA (EclipseLink)** y base de datos **MySQL**.

El objetivo del proyecto es administrar de manera sencilla la información académica de una institución educativa, permitiendo gestionar alumnos, profesores, carreras, materias, cursadas e inscripciones desde una aplicación de escritorio.

---

## Tecnologías

| Tecnología | Uso |
|------------|-----|
| Java | Lenguaje de programación |
| Java Swing | Interfaz gráfica de escritorio |
| JPA (EclipseLink) | Persistencia de datos |
| JDBC | Conexión con la base de datos |
| MySQL | Sistema gestor de base de datos |
| Maven | Gestión de dependencias y compilación |

---

## Características

- Gestión de alumnos.
- Gestión de profesores.
- Administración de carreras.
- Administración de materias.
- Gestión de cursadas.
- Registro de inscripciones.
- Consultas de información.
- Persistencia automática mediante JPA.
- Interfaz gráfica intuitiva desarrollada con Swing.

---

## Arquitectura

El proyecto está organizado siguiendo una arquitectura por capas para separar las responsabilidades de cada componente.

```
┌──────────────────────────────┐
│      Interfaz Gráfica        │
│          (Swing)             │
└──────────────┬───────────────┘
               │
               ▼
┌──────────────────────────────┐
│      Lógica de Negocio       │
│        Controladora          │
└──────────────┬───────────────┘
               │
               ▼
┌──────────────────────────────┐
│        Persistencia          │
│    JPA (EclipseLink)         │
└──────────────┬───────────────┘
               │
               ▼
┌──────────────────────────────┐
│           MySQL              │
└──────────────────────────────┘
```

---

---

## Modelo del Sistema

El sistema trabaja con diferentes entidades relacionadas entre sí.

- Alumno
- Profesor
- Carrera
- Materia
- Cursada
- Inscripción

Cada una de ellas es administrada mediante JPA y almacenada en una base de datos MySQL.

---

## Instalación

### 1. Clonar el repositorio

```bash
git clone https://github.com/tuusuario/ProyectoUniversidad.git
```

### 2. Abrir el proyecto

Puede abrirse con cualquier IDE compatible con Maven, por ejemplo:

- Apache NetBeans
- IntelliJ IDEA
- Eclipse

### 3. Configurar la base de datos

Crear una base de datos MySQL y modificar los datos de conexión en el archivo:

```
src/main/resources/META-INF/persistence.xml
```

Actualizando:

- Usuario
- Contraseña
- Nombre de la base de datos

### 4. Ejecutar

```bash
mvn clean install
```

o ejecutarlo directamente desde el IDE.

---

## Conceptos Aplicados

Durante el desarrollo del proyecto se aplicaron conceptos como:

- Programación Orientada a Objetos (POO)
- Arquitectura en Capas
- CRUD completo
- Persistencia con JPA
- Relaciones entre entidades
- Manejo de eventos
- Encapsulamiento
- Polimorfismo
- Herencia
- JDBC
- Maven

---

## Capturas

Puedes agregar capturas del sistema aquí.

```
docs/

├── menu-principal.png
├── alumnos.png
├── profesores.png
├── carreras.png
└── materias.png
```

---

## Mejoras Futuras

- Sistema de autenticación.
- Gestión de roles de usuario.
- Reportes en PDF.
- Exportación a Excel.
- Dashboard con estadísticas.
- Migración a JavaFX.
- API REST.
- Integración con PostgreSQL.

---

## Autor

**Octavio Hutter**

Estudiante de Analista de Sistemas.

Proyecto desarrollado con fines académicos para aplicar conocimientos de programación orientada a objetos, desarrollo de aplicaciones de escritorio y persistencia de datos.

---

## Licencia

Este proyecto se distribuye únicamente con fines educativos y de aprendizaje.
