# 🧪 Quality Lab — Ejercicio Día 2
## Tests expresivos y parametrizados

### ⏱️ Duración estimada
30–45 minutos

### 🧪 Tipo de tests
- Tests unitarios puros
- JUnit 5
- Sin Spring
- Sin Mockito

---

## 🎯 Objetivo del ejercicio

El objetivo de este ejercicio es **mejorar la calidad del testing**, no la cantidad.

Al finalizar deberías ser capaz de:

- Evitar tests duplicados
- Usar tests parametrizados correctamente
- Expresar **reglas de negocio** en lugar de casos aislados
- Escribir tests más cortos, claros y mantenibles
- Entender por qué **menos tests pueden significar más calidad**

---

## 🧩 Contexto

Partimos del código del **Ejercicio Día 1**, que ya incluye:

- Un `record Task` como dominio inmutable
- Un `TaskService` con el método `create(String title)`
- Validaciones de negocio en el dominio
- Tests unitarios básicos funcionando correctamente

---

## 📜 Reglas de negocio (recordatorio)

### Título (`title`)
- ❌ No puede ser `null`
- ❌ No puede estar vacío (`""`)
- ❌ No puede contener solo espacios
- ❌ Longitud máxima: **120 caracteres**

### Al crear una tarea
- `done` debe ser siempre `false`
- `createdAt` se asigna automáticamente en el momento de creación

---

## 🧪 Tareas a realizar

### A) Eliminar duplicación de tests

Revisa los tests del Día 1 y detecta aquellos que:

- Prueban la misma regla con distintos valores
- Contienen lógica repetida
- Solo cambian el input pero no el comportamiento

Estos tests deben ser **reemplazados**, no ampliados.

---

### B) Crear un test parametrizado

Implementa **un único test parametrizado** que cubra **todos los títulos inválidos**.

Debes utilizar:

- `@ParameterizedTest`
- `@MethodSource`

#### Estructura orientativa

```java
@ParameterizedTest
@MethodSource("invalidTitles")
void should_throw_exception_when_title_is_invalid(String invalidTitle) {
    assertThrows(
        IllegalArgumentException.class,
        () -> taskService.create(invalidTitle)
    );
}
```

Y un método proveedor:

```java
static Stream<String> invalidTitles() {
    return Stream.of(
        null,
        "",
        "   ",
        "a".repeat(121)
    );
}

```
### C) Decisión consciente: qué NO testear

Debes **eliminar o evitar**:

- Tests que prueban lo mismo de forma redundante
- Tests que validan mensajes de error sin aportar valor
- Tests frágiles o demasiado acoplados a la implementación

Debes poder **justificar por qué**:

- Un test parametrizado es mejor que varios tests casi iguales
- Menos tests pueden ofrecer mayor calidad y claridad

---

## ✅ Criterios de aceptación

El ejercicio se considera completado cuando:

- ✅ Existe **un único test** para títulos inválidos
- ✅ El test utiliza `@ParameterizedTest`
- ✅ No hay duplicación de lógica en los tests
- ✅ Los nombres de los tests expresan reglas de negocio
- ✅ `mvn test` pasa en verde
- ✅ El código de tests es más corto y más claro que en el Día 1

---

## 🚫 Restricciones

- ❌ No usar Spring
- ❌ No usar `jakarta.validation`
- ❌ No usar Mockito
- ❌ No añadir complejidad innecesaria
