# Historial de Desarrollo - Cascarita Python

> Cabe destacar que esta es la primera vez que utilizo un LLM para este proyecto. Su intención mas que de generar código, fue de revisar y hacer pruebas de funcionamiento, para lo que requirió generar un archivo llamado [test_funciones](I:\Cascarita\python\test_funciones.py)
>> Este tambien es un archivo generado por la IA

## Sesión: Revisión de código y pruebas de funcionamiento

### Problema inicial: Variable `punt_empate` inaccesible

**Descripción del problema:**
En `funciones.py`, la variable global `punt_empate` no era accesible dentro de la función `empate()`.

**Causa raíz:**
Python interpreta cualquier variable que recibe una asignación (`=`) dentro de una función como **variable local** por defecto. Al hacer `punt_empate = punt_max + 2` dentro de `empate()`, Python creaba una variable local nueva en lugar de modificar la global.

**Solución aplicada:**
Se agregó la declaración `global` para indicar que se debe modificar la variable global:

```python
def empate():
    global punt_empate
    global cont_p3
    punt_empate = punt_max + 2
    cont_p3 = cont_p3 + 1
```

---

### Bugs encontrados y corregidos

#### 1. `eliminarEquipo()` - Lógica de validación invertida

**Problema:** La condición `if numeroDeEquipo > len(Teams)` era incorrecta. Validaba que el índice fuera MAYOR que la longitud, cuando debería ser MENOR.

**Código antes:**
```python
def eliminarEquipo():
    imprimirEquipos()
    numeroDeEquipo = int(input("Ingrese el numero del equipo a eliminar: "))
    if numeroDeEquipo > len(Teams):  # INCORRECTO
        if len(Teams) > 0:
            Teams.pop(int(numeroDeEquipo))
    else:
        print("Ese indice de equipo no existe")
```

**Código después:**
```python
def eliminarEquipo():
    imprimirEquipos()
    numeroDeEquipo = int(input("Ingrese el numero del equipo a eliminar: "))
    if numeroDeEquipo < len(Teams):  # CORREGIDO
        if len(Teams) > 0:
            Teams.pop(int(numeroDeEquipo))
    else:
        print("Ese indice de equipo no existe")
```

#### 2. `resetearTodo()` - Iteración incorrecta sobre elementos

**Problema:** La función iteraba sobre los objetos `Equipo` en lugar de índices, e intentaba usar `Teams.pop(t)` donde `t` era un objeto, no un número.

**Código antes:**
```python
def resetearTodo():
    for t in Teams:
        Teams.pop(t)  # ERROR: t es un objeto Equipo, no un índice
    global punt_max
    punt_max = 7
```

**Código después:**
```python
def resetearTodo():
    Teams.clear()
    global punt_max
    punt_max = 7
```

---

### Pruebas de funcionamiento ejecutadas

Se creó el archivo `python/test_funciones.py` con pruebas automatizadas para verificar el correcto funcionamiento de todas las funciones.

#### Pruebas realizadas:

| # | Prueba | Descripción | Resultado |
|---|--------|-------------|-----------|
| 1 | `test_crear_equipos` | Verifica que se pueden crear y almacenar equipos correctamente | PASSED |
| 2 | `test_puntuar` | Verifica que el método `punto()` incrementa correctamente el puntaje | PASSED |
| 3 | `test_moverLocal` | Verifica que `moverLocal()` rota correctamente la lista (primero → último) | PASSED |
| 4 | `test_moverVisita` | Verifica que `moverVisita()` mueve el segundo equipo al final | PASSED |
| 5 | `test_restablecerPuntaje` | Verifica que todos los puntajes vuelven a 0 | PASSED |
| 6 | `test_empate` | Verifica que `empate()` modifica `punt_empate` y `cont_p3` correctamente | PASSED |
| 7 | `test_eliminarEquipo` | Verifica la eliminación de equipos por índice | PASSED |
| 8 | `test_resetearTodo` | Verifica que limpia equipos y resetea `punt_max` | PASSED |
| 9 | `test_logica_juego_completo` | Simula un juego completo hasta que un equipo gana | PASSED |
| 10 | `test_empate_en_juego` | Verifica detección de empate cuando ambos tienen `punt_max - 1` | PASSED |

**Resultado final: TODAS LAS PRUEBAS PASARON EXITOSAMENTE (10/10)**

---

### Archivos modificados

- `python/funciones.py` - Correcciones en `eliminarEquipo()`, `resetearTodo()`, `empate()`
- `python/main.py` - Sin modificaciones (solo revisión)
- `python/test_funciones.py` - Nuevo archivo de pruebas automatizadas

### Comando para ejecutar pruebas

```bash
cd i:\Cascarita\python && python test_funciones.py
```
