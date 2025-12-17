# 🧪 Quality Lab — Ejercicio Día 3
## Introducción a dependencias y Mockito

### ⏱️ Duración estimada
45–60 minutos

### 🧪 Tipo de tests
- Tests unitarios
- JUnit 5
- Mockito

### 🚫 Restricciones globales
- ❌ No usar Spring
- ❌ No usar base de datos real
- ❌ No usar `jakarta.validation`
- ❌ No usar contextos ni frameworks

---

## 🎯 Objetivo del ejercicio

El objetivo de este ejercicio es aprender a:

- Introducir una **dependencia externa** en un servicio
- Aislar el comportamiento del servicio mediante **mocks**
- Testear **interacciones**, no solo estado
- Entender cuándo y por qué usar Mockito

---

## 🧩 Contexto

Partimos del código existente de los ejercicios anteriores:

- Un dominio `Task` inmutable
- Un servicio de aplicación `TaskService`
- Tests unitarios funcionando correctamente
- Validaciones de negocio ya implementadas

Hasta ahora, `TaskService` no dependía de nada externo.

---

## 📜 Nuevo requisito funcional

Al crear una tarea, esta debe **persistirse** mediante un repositorio.

Para ello:

- Introduce una nueva abstracción `TaskRepository`
- El repositorio será responsable de **guardar** la tarea
- El servicio **no conoce** la implementación concreta del repositorio

---

## 🧩 Reglas de diseño

- `TaskService` debe depender de una **interfaz**, no de una implementación
- El repositorio se inyecta en el servicio
- El servicio no contiene lógica de persistencia
- El dominio no conoce al repositorio

---

## 🧪 Tareas a realizar

### A) Crear la abstracción de persistencia

- Definir una interfaz `TaskRepository`
- Declarar en ella el método necesario para guardar una tarea

---

### B) Modificar el servicio de aplicación

- Actualizar `TaskService` para que use el repositorio
- El servicio debe delegar la persistencia
- El comportamiento funcional existente no debe romperse

---

### C) Tests unitarios con Mockito

Escribir tests unitarios que verifiquen:

1. **Caso válido**
    - Cuando el título es válido:
        - se crea una tarea
        - el repositorio es invocado correctamente
        - se devuelve una tarea válida

2. **Caso inválido**
    - Cuando el título es inválido:
        - se lanza una excepción
        - el repositorio **NO** es invocado

---

## 🧠 Enfoque del testing

Los tests deben centrarse en:

- El comportamiento del servicio
- Las interacciones con el repositorio
- No en la implementación interna

---

## ✅ Criterios de aceptación

El ejercicio se considera completado cuando:

- ✅ Todos los tests pasan en verde
- ✅ Se usan mocks para el repositorio
- ✅ Se verifican interacciones con Mockito
- ✅ No hay dependencias innecesarias
- ✅ El diseño respeta separación de responsabilidades

---

## 🚨 Advertencias

- No testees la implementación del mock
- No acoples los tests a detalles internos
- No conviertas el test en un test de integración

---

## 🧠 Aprendizaje clave

> **Mockito no sirve para testear lógica,  
sirve para aislar dependencias.**

---
