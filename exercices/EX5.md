# 🧪 Quality Lab — Ejercicio Día 5
## Tests como documentación (refactor y calidad)

### ⏱️ Duración estimada
30–45 minutos

### 🧪 Tipo de tests
- Tests unitarios
- JUnit 5
- Mockito

### 🚫 Restricciones
- ❌ No añadir nueva funcionalidad
- ❌ No cambiar reglas de negocio
- ❌ No usar Spring
- ❌ No convertir tests unitarios en tests de integración

---

## 🎯 Objetivo del ejercicio

El objetivo de este ejercicio es **mejorar la calidad de los tests existentes**, no añadir comportamiento nuevo.

Al finalizar, los tests deben:

- Actuar como **documentación viva** del sistema
- Explicar claramente las reglas de negocio
- Ser fáciles de leer, mantener y razonar
- Reducir ruido y duplicación innecesaria

---

## 🧩 Contexto

Partimos del código existente de los **Días 3 y 4**, que incluye:

- `TaskService` con varios casos de uso
- `TaskRepository` mockeado en tests
- Tests unitarios que cubren happy paths y casos de error
- Uso de Mockito para verificar interacciones

Los tests **funcionan**, pero ahora deben **comunicar mejor la intención**.

---

## 🧪 Tareas a realizar

### A) Refactor de nombres de tests

Revisar todos los tests y:

- Mejorar los nombres para que expresen:
    - el contexto
    - la acción
    - el resultado esperado
- Evitar nombres genéricos o procedimentales
- Hacer posible entender el comportamiento **sin leer el cuerpo del test**

---

### B) Estructura clara de los tests (AAA)

Refactorizar los tests para que sigan claramente el patrón:

- **Arrange** — preparación de datos y mocks
- **Act** — ejecución del método bajo test
- **Assert** — verificaciones y expectativas

La estructura debe ser evidente al leer el test.

---

### C) Eliminación de ruido

Eliminar o evitar:

- Asserts que no validan el caso de uso
- Verificaciones redundantes
- Código duplicado entre tests
- Detalles irrelevantes para el comportamiento probado

Cada línea del test debe tener un propósito claro.

---

### D) Refuerzo de intención en verificaciones

Revisar los tests que usan Mockito y asegurar que:

- Se verifica **lo importante**, no todo
- Las interacciones reflejan reglas de negocio
- El uso de `ArgumentCaptor` aporta valor real
- No se acoplan los tests a detalles de implementación

---

## ✅ Criterios de aceptación

El ejercicio se considera completado cuando:

- ✅ Todos los tests pasan en verde
- ✅ Los tests se leen como una descripción del sistema
- ✅ No se ha añadido funcionalidad nueva
- ✅ El número de líneas de test se reduce o se mantiene
- ✅ El código de tests es más claro que antes del refactor

---

## 🧠 Aprendizaje clave

> **Un buen test no solo detecta errores,  
> también explica cómo funciona el sistema.**

---

## 📌 Nota final

Este ejercicio no busca “testear más”,  
sino **testear mejor**.

El resultado ideal es que alguien nuevo en el proyecto pueda entender
las reglas de negocio leyendo únicamente los tests.

---
