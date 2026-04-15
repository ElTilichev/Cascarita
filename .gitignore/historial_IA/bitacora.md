# Bitacora del proceso: Desarrollando una aplicacion con asistencia de IA
### Por Leonel Viloria - ElTilichev

> Esta es una bitacora que yo mismo realizo sin asistencia alguna de la IA, aqui se demuestra el proceso que seguí desde el que comence con la codificacion de mi aplicacion como una idea en base a Python, y como es el paso por el que la aplicacion se va desarrollando.

## Codificación Humana
En esta parte del proceso, mi intencion era generar una aplicacion para Android, que asista en una situacion de juego común en México que son las "Retas", que no son mas que partidos casuales de distintos deportes (usualmente: Voleibol, Basquetbol y Futbol; aunque las aplicacion pueden ser tantas como el mismo número de deportes agonistas existan). Enfocado en el Voleibol, necesitaba una aplicación que:
- Llevará el puntaje y que este sea variable
- Contenga los equipos que van a jugar proximamente
- Se puedan añadir equipos y tambien excluir.

Sin embargo, en mi corta carrera como desarrollador, únicamente he profundizado en el Lenguaje *Python*, lenguaje interpretado y con una curva de aprendizaje relativamente rápida, sin embargo, este lenguaje no es ni cerca de ser compatible con Android, pero, reflexione: 
> "Si puedo codificar la lógica del proceso, las necesidades las programo yo, este programa será enteramente mio, pero para poderlo llevar mas lejos, requeriré mas ayuda."

Pocos, si no es que ninguno de mis amigos están inmiscuidos en el mundo de la programación, por lo que opte por utilizar la inteligencia artificial. Más especificamente, un sistema agentico de IA. Aunque existen herramientas como "Claude Code", "Codex" y el IDE de Google "Antigravity", pense que la mejor idea es utilizar **Qwen Code**, el modelo *Qwen 3.5 Plus Coder* en especifico que es el que integra predeterminadamente el plugin ==Qwen Companion==

Ahora, dado el pequeño recorrido dentro de mis motivos, pasemos ahora al plan.

## Diseñando el plan

Los pasos son:
1. La idea
    - Se toman las ideas que dieron luz a este proyecto
2. La primera codificación.
    - Con el poco aprendizaje que tengo acerca de Python, resolví el problema que se planteo pero sin interfaz, únicamente a traves de la terminal.
3. La investigación.
    - Esta parte es vital, pues aqui doy un paso gigantezco comparado con mi conocimiento actual en el desarrollo de software. Tambien es crucial para el desarrollo de reglas e instrucciones para el agente y que se evite el "alucinar" y la aplicacion sea consistente y justo tal como la imaginamos.
4. Reunión de Recursos.
    - Con esto, se generan diferentes ideas, por ejemplo:
        1. Una idea de la interfaz y como esta se va a visualizar en el smartphone.
        2. Archivos como Agents.md y rules.md para que la IA tenga el contexto y limitaciones pertinentes.
    - Tambien se investiga como desarrollar una aplicacion, y los pasos que se necesitan para el correcto desarrollo.
5. Transformación de Código
    - La parte cumbre; ver, explorar, supervisar, aprobar o cambiar todo lo que genera la IA.

> Excento a la IA de los procesos relacionados con el sistema de versionado Git y Github, los motivos son; primero por seguridad, el segundo y más importante, aprendo a leer lo que genera y con ello entiendo el proceso de programar en otro lenguaje.

Parte del comentario anterior, es importante recalcar que el proyecto es de indole **particular y educativo** 
