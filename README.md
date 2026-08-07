# Proyecto Universidad

Sistema de gestión universitaria desarrollado en **Java** con arquitectura por capas, interfaz gráfica en **Swing**, persistencia mediante **JPA (EclipseLink)** y base de datos **MySQL**.

---

## Tecnologías

<p align="left">
  <img src="https://skillicons.dev/icons?i=java,mysql,maven,git,github" />
</p>

**Frameworks y librerías**

- Java Swing
- JPA (EclipseLink)
- JDBC
- Maven

---

## Características

- Gestión de alumnos
- Gestión de profesores
- Gestión de carreras
- Gestión de materias
- Gestión de cursadas
- Gestión de inscripciones
- Consultas de información
- Persistencia de datos mediante JPA

---

## Arquitectura

```
┌───────────────────────┐
│    Interfaz (Swing)   │
└──────────┬────────────┘
           │
┌──────────▼────────────┐
│  Lógica de Negocio    │
│    (Controladora)     │
└──────────┬────────────┘
           │
┌──────────▼────────────┐
│     Persistencia      │
│  JPA / EclipseLink    │
└──────────┬────────────┘
           │
┌──────────▼────────────┐
│       MySQL           │
└───────────────────────┘
```

---

## Estructura

```
src/
├── igu/
├── logica/
├── persistencia/
└── ProyectoUniversidad.java
```

---

## Instalación

```bash
git clone https://github.com/tuusuario/ProyectoUniversidad.git
```

Configurar la conexión en `persistence.xml` y ejecutar el proyecto desde NetBeans o cualquier IDE compatible con Maven.

---

## Conceptos implementados

- Programación Orientada a Objetos
- Arquitectura en Capas
- CRUD completo
- Persistencia con JPA
- Relaciones entre entidades
- Manejo de eventos en Swing

---

## Capturas

> Agrega aquí imágenes del sistema.

```
/docs/login.png
/docs/alumnos.png
/docs/profesores.png
```

---

## Autor

**Octavio Hutter**

Estudiante de Analista de Sistemas.

[![GitHub](https://img.shields.io/badge/GitHub-octaviohutter-181717?style=for-the-badge&logo=github)](https://github.com/TUUSUARIO)
