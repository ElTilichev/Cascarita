# Errores v0.1.2

## Criticos 

Uno de los errores mas criticos es que a la hora de ejecutar, la pantalla parpadea, la app no es estable, la logica sigue fallando, y es inutilizable.

Uno de los errores mas importantes es que siempre se modifica el "gradle-wrapper.propiertes" y el "libs.versions.toml", evita *CAMBIARLO*

## Logica
1. El contador sigue subiendo, a pesar de llegar al puntaje maximo, y el cambio lo hace tarde.

2. No se pueden eliminar equipos, y el boton de restablecer tarda mucho en surtir efecto.

## Interfaz

1. Sigue estando encimado elementos graficos, como la etiqueta de equipos, etc.

## Hipotesis

Me parece que pedirte que rebajaras la cantidad de archivos redujo la estabilidad de la app, me parece que no es necesario refactorizar, ahora es necesario que funcione en su totalidad. 