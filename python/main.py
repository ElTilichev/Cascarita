#Definimos la logica del programa o APP. Esto servira para despues ser refactorizado en otros lenguajes.

Teams = []
puntos_local = 0
puntos_visita = 0

def agregar_Equipo ():
    nuevo_equipo = input("Agregar a: ")
    Teams.append(nuevo_equipo)

def Punto_local():
    global puntos_local
    puntos_local += 1
def Punto_visita():
    global puntos_visita
    puntos_visita += 1
