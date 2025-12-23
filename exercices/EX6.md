# 🧪 Quality Lab — Ejercicio Día 6
## Introducción de Spring Boot sin romper los tests unitarios

### ⏱️ Duración estimada
45–60 minutos

### 🧪 Tipo de tests
- Tests unitarios (existentes)
- Sin añadir tests nuevos obligatoriamente

### 🚫 Restricciones
- ❌ No eliminar tests existentes
- ❌ No convertir tests unitarios en tests de integración
- ❌ No romper el aislamiento de los tests
- ❌ No mover lógica de negocio a controllers

---

## 🎯 Objetivo del ejercicio

El objetivo de este ejercicio es aprender a:

- Introducir **Spring Boot** en el proyecto
- Mantener **intactos** los tests unitarios existentes
- Separar correctamente:
    - dominio
    - aplicación
    - infraestructura
- Entender dónde empieza y dónde **NO** debe entrar Spring

---

## 🧩 Contexto

Partimos del código existente de los ejercicios anteriores:

- Dominio y servicios completamente independientes de Spring
- Tests unitarios que se ejecutan sin contexto
- Repositorios definidos como interfaces
- Lógica de negocio ya testeada

Ahora se quiere introducir Spring **sin cambiar ese núcleo**.

---

## 🧩 Tareas a realizar

### A) Añadir Spring Boot al proyecto

- Añadir dependencias de Spring Boot
- Crear la clase principal de arranque de la aplicación
- Verificar que la aplicación arranca correctamente

---

### B) Integrar el servicio con Spring

- Convertir `TaskService` en un bean de Spring
- Configurar la inyección del repositorio
- El servicio no debe depender de Spring en su firma ni lógica

---

### C) Implementar una primera infraestructura mínima

- Crear una implementación concreta de `TaskRepository`
- Esta implementación puede ser:
    - en memoria
    - falsa (stub)
    - temporal
- El objetivo es **conectar Spring**, no persistir realmente

---

### D) Mantener los tests unitarios intactos

- Los tests del Día 3–5 deben:
    - seguir pasando
    - seguir siendo unitarios
    - no arrancar contexto Spring
- No se permite añadir `@SpringBootTest` a estos tests

---

## 🧠 Enfoque de diseño

Durante este ejercicio debes reflexionar sobre:

- Qué capas conocen Spring
- Qué capas **no deberían** conocer Spring
- Dónde termina la lógica de negocio
- Dónde empieza la infraestructura

---

## ✅ Criterios de aceptación

El ejercicio se considera completado cuando:

- ✅ La aplicación Spring Boot arranca
- ✅ Todos los tests unitarios existentes pasan en verde
- ✅ El dominio no depende de Spring
- ✅ El servicio sigue siendo testeable sin Spring
- ✅ Spring se limita a configuración y wiring

---

## 🧠 Aprendizaje clave

> **Spring es un detalle de infraestructura,  
no el centro del sistema.**

---

## 📌 Nota final

Este ejercicio no busca “usar Spring”,  
sino **usarlo sin que contamine el diseño**.

---
