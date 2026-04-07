#Este codigo fue escrito por Leonel Viloria sin asistencia de IA.
#Version 0.1.1

# Definimos la logica del programa o APP. Esto servira para despues ser refactorizado en otros lenguajes.

from funciones import *

class Equipo:
    def __init__(self, nombre, puntaje):
        self.nombre = nombre
        self.puntaje = puntaje

    def punto(self):
        self.puntaje = self.puntaje + 1



if __name__ == "__main__":
    print("Cascarita")
    print("Version 0.1.1 por Tilichev® - Leonel Viloria")
    
    while True:  
        if len(Teams) >= 2:
            print("----------------------------------")
            print(f"{Teams[0].nombre} {Teams[0].puntaje} - {Teams[1].puntaje} {Teams[1].nombre}")
            print("Siguientes en la lista: ")
            for equipo in Teams[2:]:  # Solo imprime equipos desde el índice 2 (siguientes)
                print(equipo.nombre)
            
            print("1.- Punto para Local")
            print("2.- Punto para Visita")
            print("3. Agregar equipo")
            print("4. Eliminar equipo")
            print("5. Salir")
            opcion = input("Seleccione una opción: ")  # Mover la entrada fuera de la condición
            
            # Determinar opciones permitidas
            opciones_permitidas = ["3", "4", "5"]
            ganado = Teams[0].puntaje >= punt_max or Teams[1].puntaje >= punt_max
            if not ganado:
                opciones_permitidas.extend(["1", "2"])
            
            if opcion not in opciones_permitidas:
                print("Opción inválida. Por favor, selecciona una opción del 1 al 5.")
            else:
                # Permitir acciones solo si ningún equipo ha ganado aún
                if not ganado:
                    if opcion == "1":
                        Teams[0].punto()
                    elif opcion == "2":
                        Teams[1].punto()
                    elif opcion == "3":
                        agregar_Equipo()
                    elif opcion == "4":
                        eliminarEquipo()
                    elif opcion == "5":
                        break
                else:
                    # Si ya alguien ganó, no permitir puntuar, pero sí otras opciones
                    if opcion == "3":
                        agregar_Equipo()
                    elif opcion == "4":
                        eliminarEquipo()
                    elif opcion == "5":
                        break
            
            # Verificar si alguien ganó después de la acción
            if Teams[0].puntaje >= punt_max:
                print(f"El equipo {Teams[0].nombre} ha ganado")
                moverVisita()
                restablecerPuntaje()
            elif Teams[1].puntaje >= punt_max:
                print(f"El equipo {Teams[1].nombre} ha ganado")
                moverLocal()
                restablecerPuntaje()
                
        else:
            if len(Teams) < 2:
                print("Debe agregar al menos dos equipos para comenzar el juego")
                while len(Teams) < 2:
                    agregar_Equipo()
            continue

