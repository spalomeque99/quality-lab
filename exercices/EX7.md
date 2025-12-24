# 🧪 Quality Lab — Ejercicio Día 7
## Tests de Integración con Spring Boot (sin mocks)

### ⏱️ Duración estimada
60–90 minutos

### 🧪 Tipo de tests
- Tests de integración (Spring Context)
- JUnit 5
- Sin Mockito (salvo casos muy justificados)

### 🚫 Restricciones
- ❌ No tocar las reglas de negocio
- ❌ No romper ni modificar los tests unitarios existentes
- ❌ No usar base de datos real todavía (hoy NO Testcontainers)
- ❌ No “mockear” el repositorio en tests de integración

---

## 🎯 Objetivo del ejercicio

El objetivo de este ejercicio es aprender a:

- Arrancar un **contexto Spring real** en tests
- Probar un caso de uso de punta a punta **a través del wiring**
- Diferenciar claramente:
    - unit tests (rápidos, aislados)
    - integration tests (Spring, wiring real)
- Validar que tu infraestructura mínima (`InMemoryTaskRepository`) funciona con el servicio

---

## 🧩 Contexto

Ya tienes:

- `TaskService` como bean (directamente o vía `@Configuration`)
- `TaskRepository` como interfaz en `application`
- `InMemoryTaskRepository` como implementación en `infrastructure` (bean Spring)
- Tests unitarios (Días 3–6) que pasan sin Spring

Ahora toca asegurar que, con Spring arrancado, **todo encaja**.

---

## 🧪 Tareas a realizar

### A) Crear una suite de tests de integración

- Crear un nuevo test en un paquete separado, por ejemplo:
    - `com.quality.quality_lab.integration`
- El test debe arrancar el contexto Spring con:
    - `@SpringBootTest` (o alternativa equivalente si sabes lo que haces)

---

### B) Test de integración: caso de uso “crear tarea”

Crear un test que:

1. Obtenga `TaskService` desde el contexto (inyección)
2. Llame al caso de uso de crear tarea con un título válido
3. Verifique que:
    - devuelve una `Task` válida
    - `done` es `false`
    - `createdAt` está asignado
    - la tarea recibe un `id` (si tu repo in-memory lo asigna)

---

### C) Test de integración: caso de uso “finalizar tarea”

Crear un test que:

1. Cree una tarea
2. Finalice esa misma tarea por id
3. Verifique que:
    - la tarea finalizada tiene `done = true`
    - mantiene el `title`
    - mantiene el `createdAt` (no se “resetea”)
    - sigue teniendo el mismo `id`

---

### D) Test de integración: errores esperados

Añadir al menos **un test** que verifique un error real en integración, por ejemplo:

- finalizar una tarea inexistente
- finalizar una tarea ya completada

En estos tests:
- se espera excepción
- no se debe “forzar” el error con mocks

---

## 🧠 Enfoque del testing

- Estos tests prueban el **wiring**: Spring + beans + implementación real del repositorio.
- No prueban cada rama al detalle como un unit test.
- Deben ser **pocos**, pero **valiosos**.

---

## ✅ Criterios de aceptación

El ejercicio se considera completado cuando:

- ✅ `mvn test` pasa en verde
- ✅ Los unit tests siguen siendo unitarios (sin Spring)
- ✅ Existen tests de integración separados (package `integration`)
- ✅ Los tests de integración usan wiring real (sin mocks del repo)
- ✅ Se prueban al menos:
    - crear tarea
    - finalizar tarea
    - un error en integración

---

## 🧠 Aprendizaje clave

> **Los unit tests validan comportamiento en aislamiento.  
> Los integration tests validan que el sistema “cableado” funciona.**

---
