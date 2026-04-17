# Errores en la version 0.1.1

## Interfaz Gráfica
1. Los marcadores tienen una "+" en lugar de funcionar todo como un solo botón. Tienen que ser todo un bloque completo de uso.
2. En el bloque de "próximos equipos" se encuentra tapada parcialmente por el bloque donde se encuentran los marcadores.
3. No se muestra una pantalla de inicio previo a cargar todo
4. La pestaña "Historial" puede ser omitido y quitado de la interfaz gráfica.
5. En ajustes, aun no es necesario anexar algo, pero seria bueno colocar los créditos:
	1. Leonel Viloria
	2. ElTilichev

## Funcionamiento
1. La aplicación se queda tal como se abrió anteriormente, a pesar de que se fuerce el cierre en Android. 
2. Aunado al error anterior, la aplicacion ya no puede subir el puntaje de ninguno de los dos equipos, tampoco podemos agregar equipos, quitarlos ni moverlos
3. El boton "reiniciar todo" no funciona.
4. Mismo caso con el "Puntaje para ganar", ni aumenta ni disminuye
5. El boton de "x" para quitar un equipo.

## Refactoring
1. El proyecto consta de bastantes archivos que se van ligando uno a otro, trata de refactorizar para que logres el funcionamiento de la app con menor cantidad de archivos.