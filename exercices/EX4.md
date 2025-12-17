# 🧪 Quality Lab — Ejercicio Día 4
## Nuevo caso de uso: marcar una tarea como completada

### ⏱️ Duración estimada
45–60 minutos

### 🧪 Tipo de tests
- Tests unitarios
- JUnit 5
- Mockito

### 🚫 Restricciones
- ❌ No usar Spring
- ❌ No usar base de datos real
- ❌ No usar `jakarta.validation`
- ❌ No usar tests de integración

---

## 🎯 Objetivo del ejercicio

El objetivo de este ejercicio es aprender a:

- Añadir un **nuevo caso de uso** en el servicio de aplicación
- Introducir lógica de negocio basada en **estado**
- Testear **ramas de comportamiento** (happy path y errores)
- Seguir usando Mockito con criterio

---

## 🧩 Contexto

Partimos del código existente de los ejercicios anteriores:

- Dominio `Task` inmutable
- Servicio `TaskService` con repositorio inyectado
- Tests unitarios con mocks funcionando correctamente
- Validaciones de negocio ya implementadas

---

## 📜 Nuevo caso de uso

Implementar un nuevo método en el servicio de aplicación:

> Marcar una tarea existente como completada.

Este caso de uso **NO crea** una nueva tarea, sino que **modifica el estado lógico** de una existente.

---

## 📐 Reglas de negocio

1. La tarea debe **existir**
2. Si la tarea **no existe**, se debe lanzar una excepción
3. Si la tarea **ya está completada**, se debe lanzar una excepción
4. Al marcarla como completada:
    - `done` pasa a `true`
    - la tarea resultante se persiste mediante el repositorio

---

## 🧩 Reglas de diseño

- El servicio es el responsable del caso de uso
- El dominio sigue siendo inmutable
- No se permite modificar la tarea “en sitio”
- El repositorio es el único responsable de persistir

---

## 🧪 Tareas a realizar

### A) Extender el repositorio

- Añadir el método necesario para recuperar una tarea existente
- La abstracción debe permitir identificar una tarea por su id

---

### B) Implementar el caso de uso en el servicio

- Añadir un nuevo método en `TaskService`
- El método debe:
    - recuperar la tarea
    - validar las reglas de negocio
    - devolver la tarea marcada como completada

---

### C) Tests unitarios con Mockito

Escribir tests unitarios que cubran:

1. **Happy path**
    - La tarea existe
    - No está completada
    - Se marca como completada
    - Se persiste el resultado

2. **Tarea inexistente**
    - Se lanza una excepción
    - El repositorio no persiste nada

3. **Tarea ya completada**
    - Se lanza una excepción
    - El repositorio no persiste nada

---

## 🧠 Enfoque del testing

Los tests deben centrarse en:

- Comportamiento del servicio
- Validación de reglas de negocio
- Interacciones con el repositorio
- No en detalles de implementación

---

## ✅ Criterios de aceptación

El ejercicio se considera completado cuando:

- ✅ Todos los tests pasan en verde
- ✅ Se usan mocks correctamente
- ✅ Se verifican interacciones con el repositorio
- ✅ Las reglas de negocio están cubiertas por tests
- ✅ El código mantiene separación de responsabilidades

---

## 🧠 Aprendizaje clave

> **Un servicio de aplicación coordina reglas de negocio,  
no almacena estado ni conoce detalles técnicos.**

---
