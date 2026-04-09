Teams = []
punt_max = 7
punt_empate = punt_max
cont_p3 = 0
desempate = 3

class Equipo:
    def __init__(self, nombre, puntaje):
        self.nombre = nombre
        self.puntaje = puntaje

    def punto(self):
        self.puntaje = self.puntaje + 1

def agregar_Equipo():
    Teams.append(Equipo(input("Cual es el nombre del equipo: "), 0))    

def resetearTodo():
    Teams.clear()
    global punt_max
    punt_max = 7

def moverLocal():  # Mueve el equipo local al final de la lista y el equipo de visita se convierte en local, y la lista de equipos se recorre hacia arriba
    Teams.append(Teams[0])
    for i in range(len(Teams)-1):
        Teams[i] = Teams[i+1]
    Teams.pop(len(Teams)-1)

def moverVisita():
    Teams.append(Teams[1])
    for i in range(1, len(Teams)-1):
        Teams[i] = Teams[i+1]
    Teams.pop(len(Teams)-1)

def imprimirEquipos(): #aprendizaje: Enumerate toma un objeto y lo itera ennumerando, debes especificar el inicio. 
    for i, equipo in enumerate(Teams, start=0):
        print(f"{i}.- {equipo.nombre}")

def eliminarEquipo():
    imprimirEquipos()
    numeroDeEquipo = int(input("Ingrese el numero del equipo a eliminar: "))
    if numeroDeEquipo < len(Teams):
        if len(Teams) > 0:
            Teams.pop(int(numeroDeEquipo))
    else:
        print("Ese indice de equipo no existe")
        
def restablecerPuntaje():
    for equipo in Teams:
        equipo.puntaje = 0

def empate():
    global punt_empate
    global cont_p3
    punt_empate = punt_max + 2
    cont_p3 = cont_p3 + 1 

