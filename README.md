# 🎓 Proyecto Universidad

Sistema de gestión universitaria desarrollado en **Java** utilizando una arquitectura por capas, interfaz gráfica con **Swing**, persistencia mediante **JPA (EclipseLink)** y base de datos **MySQL**.

El proyecto permite administrar los principales elementos de una institución educativa, como alumnos, profesores, carreras, materias, cursadas e inscripciones.

---

## 📷 Vista General

> Sistema de escritorio desarrollado como proyecto académico para aplicar conceptos de Programación Orientada a Objetos, Persistencia de Datos y Arquitectura en Capas.

---

# ✨ Características

- 👨‍🎓 Gestión de alumnos
- 👨‍🏫 Gestión de profesores
- 🎓 Gestión de carreras
- 📚 Gestión de materias
- 📝 Gestión de cursadas
- 📌 Gestión de inscripciones
- 🔍 Consultas de información
- 💾 Persistencia de datos con JPA
- 🖥️ Interfaz gráfica realizada con Java Swing

---

# 🛠 Tecnologías Utilizadas

| Tecnología | Uso |
|------------|-----|
| Java | Lenguaje principal |
| Swing | Interfaz gráfica |
| Maven | Gestión de dependencias |
| JPA | Persistencia de datos |
| EclipseLink | Implementación de JPA |
| MySQL | Base de datos |
| JDBC | Conexión con MySQL |

---

# 📂 Estructura del Proyecto

```
src/
│
├── igu/
│   ├── PanelAlumno
│   ├── PanelProfesor
│   ├── PanelCarrera
│   ├── PanelMateria
│   ├── PanelCursada
│   ├── PanelInscripciones
│   ├── PanelConsultas
│   └── VentanaPrincipal
│
├── logica/
│   ├── Alumno
│   ├── Profesor
│   ├── Carrera
│   ├── Materia
│   ├── Cursada
│   └── Controladora
│
└── persistencia/
    └── ControladoraPersistencia
```

---

# 🏗 Arquitectura

El proyecto sigue una arquitectura por capas:

```
Interfaz Gráfica (Swing)
          │
          ▼
Capa Lógica (Controladora)
          │
          ▼
Persistencia (JPA)
          │
          ▼
      Base de Datos
```

Esta separación facilita el mantenimiento, la escalabilidad y la reutilización del código.

---

# 🧩 Modelo del Sistema

El sistema administra entidades relacionadas entre sí, entre ellas:

- Alumno
- Profesor
- Carrera
- Materia
- Cursada
- Inscripción

Estas entidades son persistidas mediante JPA utilizando EclipseLink.

---

# 🚀 Instalación

## 1. Clonar el repositorio

```bash
git clone https://github.com/usuario/ProyectoUniversidad.git
```

## 2. Abrir el proyecto

Puede abrirse con:

- Apache NetBeans
- IntelliJ IDEA
- Eclipse

---

## 3. Configurar MySQL

Crear una base de datos y actualizar los datos de conexión en el archivo de configuración correspondiente (`persistence.xml`).

---

## 4. Ejecutar

Desde Maven:

```bash
mvn clean install
```

o ejecutar directamente la clase principal desde el IDE.

---

# 📚 Conceptos Aplicados

- Programación Orientada a Objetos
- Encapsulamiento
- Herencia
- Polimorfismo
- Arquitectura en Capas
- Patrón Controlador
- Persistencia con JPA
- Relaciones entre entidades
- CRUD completo
- Manejo de eventos en Swing

---

# 🎯 Objetivos del Proyecto

Este proyecto fue desarrollado con fines académicos para poner en práctica conocimientos sobre:

- Desarrollo de aplicaciones de escritorio
- Persistencia de datos
- Modelado de entidades
- Programación Orientada a Objetos
- Gestión de bases de datos
- Arquitectura de software

---

# 📈 Posibles Mejoras

- Autenticación de usuarios
- Control de roles
- Exportación de reportes PDF
- Estadísticas del sistema
- Dashboard
- Migración a JavaFX
- API REST
- Integración con PostgreSQL

---

# 👨‍💻 Autor

**Octavio Hutter**

Estudiante de Analista de Sistemas

Desarrollador Java • SQL • Desarrollo de Software

---

# 📄 Licencia

Este proyecto fue desarrollado con fines educativos y académicos.
