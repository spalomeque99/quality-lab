# 🧪 Quality Lab — Ejercicio Día 9
## Persistencia real con JPA + Tests de Integración

### ⏱️ Duración estimada
90–120 minutos

### 🧪 Tipo de tests
- Tests de integración
- Spring Boot
- JPA / Hibernate
- Base de datos en memoria (H2)
- Sin mocks en integración

---

## 🎯 Objetivo del ejercicio

El objetivo de este ejercicio es aprender a:

- Introducir **persistencia real** con JPA
- Implementar un repositorio real basado en base de datos
- Probar el sistema con **tests de integración reales**
- Validar que:
    - el dominio
    - la aplicación
    - la infraestructura
      funcionan juntas con una base de datos

---

## 🧩 Contexto

Hasta ahora tienes:

- Dominio y casos de uso bien definidos
- Tests unitarios sólidos
- Tests de integración con repositorio en memoria
- Capa web probada en aislamiento

Ahora se sustituye el repositorio en memoria por una **implementación JPA real**.

---

## 🧩 Nuevo requisito técnico

Persistir las tareas en una base de datos relacional usando JPA.

---

## 📐 Reglas de diseño (muy importantes)

- ❌ No mover lógica de negocio al repositorio
- ❌ No acoplar el dominio a JPA innecesariamente
- ❌ No romper los tests unitarios existentes
- ❌ No usar mocks en tests de integración

- ✅ El servicio sigue siendo el centro del caso de uso
- ✅ JPA es solo un detalle de infraestructura

---

## 🧪 Tareas a realizar

### A) Crear una entidad JPA

- Crear una entidad que represente una tarea persistida
- Decidir:
    - si el dominio es también entidad
    - o si usas un modelo de persistencia separado
- Definir:
    - `@Id`
    - estrategia de generación
    - columnas necesarias

---

### B) Implementar repositorio JPA

- Crear una implementación de `TaskRepository` basada en JPA
- Usar:
    - `EntityManager`
    - o `Spring Data JPA`
- El repositorio debe:
    - guardar tareas
    - recuperar tareas por id

---

### C) Configuración de base de datos para tests

- Usar H2 en memoria para tests
- Configurar:
    - `ddl-auto`
    - dialecto si es necesario
- Asegurar que los tests son reproducibles

---

### D) Tests de integración con base de datos

Crear tests que:

1. Arranquen Spring con JPA
2. Usen la base de datos real (H2)
3. Prueben:
    - crear tarea
    - finalizar tarea
    - errores reales (task inexistente, task ya completada)

Estos tests deben:
- NO usar Mockito
- NO usar repositorios in-memory
- Validar comportamiento completo

---

## 🧠 Enfoque del testing

- Estos tests prueban:
    - persistencia
    - mapping
    - transacciones
    - wiring real
- No prueban:
    - cada rama de negocio (eso ya está cubierto por unit tests)

---

## ✅ Criterios de aceptación

El ejercicio se considera completado cuando:

- ✅ JPA persiste datos correctamente
- ✅ Los tests de integración pasan en verde
- ✅ La base de datos se limpia entre tests
- ✅ Los unit tests siguen pasando
- ✅ El diseño sigue respetando capas

---

## 🧠 Aprendizaje clave

> **La base de datos es un detalle.  
> El comportamiento sigue viviendo en la aplicación.**

---

## 📌 Nota final

Este ejercicio es el primer contacto serio con **persistencia real**.  
No busques optimizar ni cubrirlo todo: busca **entender el flujo completo**.

---
