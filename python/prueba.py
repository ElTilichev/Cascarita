
lista = ["equipo1", "equipo2", "equipo3"]

print(lista)

def moverEquiposArriba(lista):
    lista.append(lista[0])
    for i in range(len(lista)-1):
        lista[i] = lista[i+1]
    lista.pop(len(lista)-1)

def moverEquiposdesde1(lista):
    lista.append(lista[1])
    for i in range(1,len(lista)-1):
        lista[i] = lista[i+1]
    lista.pop(len(lista)-1)

print(f"{lista[0]} vs {lista[1]}")
moverEquiposdesde1(lista)
print(lista)
print(f"{lista[0]} vs {lista[1]}")
moverEquiposdesde1(lista)
print(lista)
print(f"{lista[0]} vs {lista[1]}")
moverEquiposdesde1(lista)
print(lista)
print(f"{lista[0]} vs {lista[1]}")