# EXCERCICES
# Ejercicio — Día 1
## TaskService.create() + Tests Unitarios

**Duración:** ~30 minutos  
**Objetivo:** empezar el laboratorio con tests de calidad, sin Spring.

---

## Contexto

Vas a implementar el primer caso de uso de tu mini-monolito: **crear una Task**.

Este ejercicio está diseñado para:
- trabajar **testing unitario puro**
- practicar **reglas de negocio claras**
- evitar dependencias innecesarias (Spring, DB, etc.)

---

## Reglas de negocio

1. **Título obligatorio**
    - no puede ser `null`
    - no puede ser vacío
    - no puede contener solo espacios

2. **Longitud máxima**
    - `title` debe tener **máximo 120 caracteres**

3. **Valores al crear**
    - `done` debe ser siempre `false`
    - `createdAt` debe asignarse en el momento de creación

---

## Objetivo técnico

- ❌ No usar Spring (`@SpringBootTest`, contextos, etc.)
- ✅ Tests unitarios puros con **JUnit 5**
- ⚠️ Mockito solo si lo consideras estrictamente necesario (idealmente no)
- Tests rápidos, deterministas y legibles

---

## Tareas

### A) Modelo de dominio

Crea una clase (o `record`, a tu elección) llamada `Task` con al menos:

- `id` (puede ser `null` por ahora)
- `title`
- `done`
- `createdAt`

> 💡 El tipo de `id` queda a tu elección (`UUID`, `Long`, etc.).  
> Hoy **no es relevante** para el ejercicio.

---

### B) Servicio de aplicación

Crea una clase `TaskService` con un método:

Este método debe:
- validar todas las reglas de negocio
- crear y devolver una `Task` válida

Si el `title` no es válido:
- lanza una excepción clara
- con un mensaje razonable y entendible

> 💡 Decide tú si haces `trim()` al título o no.  
> Si lo haces, documenta la decisión.

---

### C) Tests unitarios (mínimo 2)

#### 1. Happy path
Con un `title` válido, el método `create` debe:
- devolver una `Task`
- con `done = false`
- con `createdAt` asignado
- con el `title` esperado

#### 2. Caso inválido
Con un `title` inválido (elige uno):
- `null`
- vacío
- solo espacios
- más de 120 caracteres

El método debe:
- lanzar una excepción
- y **no devolver ninguna Task**

---

## Criterios de aceptación

Para dar el ejercicio por válido:

- ✅ `mvn test` pasa en verde
- ✅ No se arranca ningún contexto Spring
- ✅ La validación está en el código de producción (no en los tests)
- ✅ Los tests tienen nombres claros y expresan comportamiento
- ✅ Los tests no son frágiles (no compares timestamps exactos)