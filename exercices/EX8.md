# 🧪 Quality Lab — Ejercicio Día 8
## API REST + Tests de Capa Web (MockMvc)

### ⏱️ Duración estimada
60–90 minutos

### 🧪 Tipo de tests
- Tests de capa web
- Spring Boot
- MockMvc
- Sin base de datos real
- Sin Testcontainers

---

## 🎯 Objetivo del ejercicio

El objetivo de este ejercicio es aprender a:

- Exponer los casos de uso mediante una **API REST**
- Separar correctamente:
    - capa web
    - capa de aplicación
- Testear la **capa web en aislamiento**
- Verificar contratos HTTP (status, body, errores)
- Entender la diferencia entre:
    - tests unitarios
    - tests de integración
    - tests de capa web

---

## 🧩 Contexto

Hasta ahora tienes:

- Dominio y aplicación bien testeados
- Infraestructura mínima funcional
- Tests unitarios (Días 1–5)
- Tests de integración con Spring (Día 7)

Ahora toca añadir la **capa web**, sin mover lógica de negocio.

---

## 🧩 Nuevo requisito funcional

Exponer los siguientes casos de uso vía HTTP:

1. Crear una tarea
2. Finalizar una tarea por id

La API debe ser **simple, clara y coherente**.

---

## 📐 Reglas de diseño (muy importantes)

- Los controllers:
    - ❌ NO contienen lógica de negocio
    - ❌ NO crean entidades de dominio directamente
    - ✅ Delegan siempre en `TaskService`
- El dominio y la aplicación **no conocen HTTP**
- La capa web traduce:
    - HTTP → aplicación
    - aplicación → HTTP

---

## 🧪 Tareas a realizar

### A) Crear el controller REST

- Crear un controller en un paquete `web` o `controller`
- Definir endpoints para:
    - crear tarea
    - finalizar tarea
- Usar DTOs de entrada/salida si lo consideras necesario

---

### B) Tests de capa web con MockMvc

Crear tests que:

- Arranquen solo la capa web
- Usen `MockMvc`
- **NO usen base de datos real**
- **NO prueben infraestructura**
- Mockeen la capa de aplicación (`TaskService`)

---

### C) Casos a cubrir en tests web

Al menos:

1. **Crear tarea (happy path)**
    - Status HTTP correcto
    - Body con los datos esperados

2. **Crear tarea inválida**
    - Status de error adecuado
    - Mensaje claro

3. **Finalizar tarea existente**
    - Status HTTP correcto
    - Body actualizado

4. **Finalizar tarea inexistente**
    - Status de error adecuado

---

## 🧠 Enfoque del testing

Estos tests deben validar:

- Contrato HTTP
- Serialización / deserialización
- Mapeo de errores
- No reglas de negocio internas

---

## ✅ Criterios de aceptación

El ejercicio se considera completado cuando:

- ✅ Existen endpoints REST funcionales
- ✅ Los tests web pasan en verde
- ✅ Los tests web no arrancan repositorios reales
- ✅ La lógica de negocio sigue fuera del controller
- ✅ El diseño mantiene separación de capas

---

## 🧠 Aprendizaje clave

> **Un controller no decide nada,  
solo traduce protocolos.**

---

## 📌 Nota final

Este ejercicio consolida la idea de que **cada tipo de test prueba una cosa distinta**:

- Unit → comportamiento
- Integration → wiring
- Web → contrato HTTP

---