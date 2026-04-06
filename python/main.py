#Definimos la logica del programa o APP. Esto servira para despues ser refactorizado en otros lenguajes.

Teams = []
punt_max = 7

class Equipo:
    def __init__(self, nombre, puntaje):
        self.nombre = nombre
        self.puntaje = puntaje

    def punto(self):
        self.puntaje += 1 

def agregar_Equipo ():
    Teams.append(Equipo(input("Cual es el nombre del equipo"), 0))    

def moverLocal(): #Mueve el equipo local al final de la lista y el equipo de visita se convierte en local, y la lista de equipos se recorre hacia arriba
    Teams.append(Teams[0])
    for i in range(len(Teams)-1):
        Teams[i] = Teams[i+1]
    Teams.pop(len(Teams)-1)
def moverVisita():
    Teams.append(Teams[1])
    for i in range(1,len(Teams)-1):
        Teams[i] = Teams[i+1]
    Teams.pop(len(Teams)-1)
def imprimirEquipos():
    for equipo in Teams:
        print(equipo.nombre)
# Pruebas de funcionalidad de las funciones
"""agregar_Equipo()
print(Teams[0].nombre)
Teams[0].punto()
print(Teams[0].puntaje)
agregar_Equipo()
print(Teams[1].nombre)
Teams[1].punto()
print(Teams[1].puntaje)
print(f"{Teams[0].nombre} {Teams[0].puntaje} - {Teams[1].puntaje} {Teams[1].nombre}")
"""
if __name__ == "__main__":
    agregar_Equipo()
    agregar_Equipo()
    agregar_Equipo()

    imprimirEquipos()
    print(f"{Teams[0].nombre} {Teams[0].puntaje} - {Teams[1].puntaje} {Teams[1].nombre}")
    moverVisita()
    imprimirEquipos()
    print(f"{Teams[0].nombre} {Teams[0].puntaje} - {Teams[1].puntaje} {Teams[1].nombre}")
    moverVisita()
    imprimirEquipos()
    print(f"{Teams[0].nombre} {Teams[0].puntaje} - {Teams[1].puntaje} {Teams[1].nombre}")