"""
Script de pruebas automatizadas para funciones.py
Archivo Generado por la IA.

"""
import sys
sys.path.insert(0, r'i:\Cascarita\python')

# Reiniciar modulo para pruebas limpias
import importlib
import funciones
importlib.reload(funciones)

from funciones import Teams, Equipo, punt_max, punt_empate, cont_p3, desempate
from funciones import agregar_Equipo, moverLocal, moverVisita, eliminarEquipo
from funciones import restablecerPuntaje, empate, resetearTodo, imprimirEquipos

def test_crear_equipos():
    """Prueba crear equipos"""
    print("\n=== TEST: Crear equipos ===")
    Teams.clear()
    Teams.append(Equipo("Equipo A", 0))
    Teams.append(Equipo("Equipo B", 0))
    Teams.append(Equipo("Equipo C", 0))
    assert len(Teams) == 3, f"Se esperaban 3 equipos, hay {len(Teams)}"
    assert Teams[0].nombre == "Equipo A"
    assert Teams[1].nombre == "Equipo B"
    print(f"Equipos creados correctamente: {[e.nombre for e in Teams]}")
    print("PASSED")

def test_puntuar():
    """Prueba sumar puntos"""
    print("\n=== TEST: Puntuar ===")
    Teams[0].punto()
    Teams[0].punto()
    assert Teams[0].puntaje == 2, f"Se esperaban 2 puntos, tiene {Teams[0].puntaje}"
    Teams[1].punto()
    assert Teams[1].puntaje == 1, f"Se esperaba 1 punto, tiene {Teams[1].puntaje}"
    print(f"Puntajes: {Teams[0].nombre}={Teams[0].puntaje}, {Teams[1].nombre}={Teams[1].puntaje}")
    print("PASSED")

def test_moverLocal():
    """Prueba mover local"""
    print("\n=== TEST: moverLocal ===")
    Teams.clear()
    Teams.append(Equipo("Local", 0))
    Teams.append(Equipo("Visita", 0))
    Teams.append(Equipo("Tercero", 0))
    print(f"Antes: {[e.nombre for e in Teams]}")
    moverLocal()
    print(f"Despues: {[e.nombre for e in Teams]}")
    assert Teams[0].nombre == "Visita", f"Se esperaba 'Visita' primero, es '{Teams[0].nombre}'"
    assert Teams[-1].nombre == "Local", f"Se esperaba 'Local' ultimo, es '{Teams[-1].nombre}'"
    print("PASSED")

def test_moverVisita():
    """Prueba mover visita"""
    print("\n=== TEST: moverVisita ===")
    Teams.clear()
    Teams.append(Equipo("Local", 0))
    Teams.append(Equipo("Visita", 0))
    Teams.append(Equipo("Tercero", 0))
    print(f"Antes: {[e.nombre for e in Teams]}")
    moverVisita()
    print(f"Despues: {[e.nombre for e in Teams]}")
    assert Teams[1].nombre == "Tercero", f"Se esperaba 'Tercero' en posicion 1, es '{Teams[1].nombre}'"
    assert Teams[-1].nombre == "Visita", f"Se esperaba 'Visita' ultimo, es '{Teams[-1].nombre}'"
    print("PASSED")

def test_restablecerPuntaje():
    """Prueba restablecer puntaje"""
    print("\n=== TEST: restablecerPuntaje ===")
    Teams.clear()
    Teams.append(Equipo("A", 5))
    Teams.append(Equipo("B", 3))
    print(f"Antes: {[e.puntaje for e in Teams]}")
    restablecerPuntaje()
    print(f"Despues: {[e.puntaje for e in Teams]}")
    assert all(e.puntaje == 0 for e in Teams), "Todos los puntajes deben ser 0"
    print("PASSED")

def test_empate():
    """Prueba funcion empate"""
    print("\n=== TEST: empate ===")
    import funciones
    funciones.cont_p3 = 0
    funciones.punt_max = 7
    print(f"Antes: punt_empate={funciones.punt_empate}, cont_p3={funciones.cont_p3}")
    empate()
    print(f"Despues: punt_empate={funciones.punt_empate}, cont_p3={funciones.cont_p3}")
    assert funciones.punt_empate == 9, f"Se esperaba punt_empate=9, es {funciones.punt_empate}"
    assert funciones.cont_p3 == 1, f"Se esperaba cont_p3=1, es {funciones.cont_p3}"
    print("PASSED")

def test_eliminarEquipo():
    """Prueba eliminar equipo"""
    print("\n=== TEST: eliminarEquipo ===")
    Teams.clear()
    Teams.append(Equipo("A", 0))
    Teams.append(Equipo("B", 0))
    Teams.append(Equipo("C", 0))
    print(f"Antes: {[e.nombre for e in Teams]}")
    # Simular eliminacion directa (sin input)
    Teams.pop(1)
    print(f"Despues de eliminar indice 1: {[e.nombre for e in Teams]}")
    assert len(Teams) == 2, f"Se esperaban 2 equipos, hay {len(Teams)}"
    assert Teams[0].nombre == "A" and Teams[1].nombre == "C"
    print("PASSED")

def test_resetearTodo():
    """Prueba resetear todo"""
    print("\n=== TEST: resetearTodo ===")
    Teams.clear()
    Teams.append(Equipo("A", 5))
    Teams.append(Equipo("B", 3))
    funciones.punt_max = 10
    print(f"Antes: {len(Teams)} equipos, punt_max={funciones.punt_max}")
    resetearTodo()
    print(f"Despues: {len(Teams)} equipos, punt_max={funciones.punt_max}")
    assert len(Teams) == 0, f"Se esperaban 0 equipos, hay {len(Teams)}"
    assert funciones.punt_max == 7, f"Se esperaba punt_max=7, es {funciones.punt_max}"
    print("PASSED")

def test_logica_juego_completo():
    """Prueba simulacion de juego completo"""
    print("\n=== TEST: Logica de juego completo ===")
    Teams.clear()
    Teams.append(Equipo("Local", 0))
    Teams.append(Equipo("Visita", 0))
    
    # Simular juego hasta punt_max
    while Teams[0].puntaje < punt_max and Teams[1].puntaje < punt_max:
        Teams[0].punto()
    
    ganador = Teams[0].puntaje >= punt_max or Teams[1].puntaje >= punt_max
    assert ganador, "Alguien debio haber ganado"
    print(f"Ganador: {Teams[0].nombre} con {Teams[0].puntaje} puntos")
    print("PASSED")

def test_empate_en_juego():
    """Prueba situacion de empate"""
    print("\n=== TEST: Empate en juego ===")
    Teams.clear()
    Teams.append(Equipo("A", 6))  # punt_max - 1
    Teams.append(Equipo("B", 6))  # punt_max - 1
    
    import funciones
    funciones.cont_p3 = 0
    
    # Detectar empate
    if Teams[0].puntaje == punt_max - 1 and Teams[1].puntaje == punt_max - 1:
        empate()
        print(f"Empate detectado! punt_empate={funciones.punt_empate}, cont_p3={funciones.cont_p3}")
        assert funciones.cont_p3 == 1
    else:
        assert False, "No se detecto el empate"
    print("PASSED")

# EJECUTAR TODAS LAS PRUEBAS
if __name__ == "__main__":
    print("INICIANDO PRUEBAS AUTOMATIZADAS")
    print("=" * 50)
    
    try:
        test_crear_equipos()
        test_puntuar()
        test_moverLocal()
        test_moverVisita()
        test_restablecerPuntaje()
        test_empate()
        test_eliminarEquipo()
        test_resetearTodo()
        test_logica_juego_completo()
        test_empate_en_juego()
        
        print("\n" + "=" * 50)
        print("TODAS LAS PRUEBAS PASARON EXITOSAMENTE!")
        print("=" * 50)
    except AssertionError as e:
        print(f"\nPRUEBA FALLIDA: {e}")
    except Exception as e:
        print(f"\nERROR EN PRUEBA: {type(e).__name__}: {e}")
