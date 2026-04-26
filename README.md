# Cascarita - App de Voleibol Android

Aplicación Android para gestionar partidos de voleibol de manera casual ("cascarita"). Permite crear equipos, llevar el marcador y configurar las reglas del juego.

## 📁 Ruta del Proyecto

```
/workspace/App_Cascarita/
├── app/                              # Módulo principal de la aplicación
│   ├── src/main/
│   │   ├── AndroidManifest.xml       # Configuración de permisos y componentes
│   │   ├── kotlin/com/cascarita/app/
│   │   │   ├── core/                 # Núcleo de la aplicación
│   │   │   │   ├── common/           # Utilidades y constantes compartidas
│   │   │   │   ├── di/               # Módulos de inyección de dependencias (Hilt)
│   │   │   │   ├── database/         # Base de datos Room (Entidades, DAOs)
│   │   │   │   └── theme/            # Design System (colores, tipografía, temas)
│   │   │   ├── feature/              # Características de la app
│   │   │   │   ├── game/             # Feature del juego (partido, marcador)
│   │   │   │   │   ├── data/         # Capa de datos (repositorios, fuentes)
│   │   │   │   │   ├── domain/       # Capa de dominio (casos de uso, modelos)
│   │   │   │   │   ├── presentation/ # Capa de presentación (ViewModels, UI)
│   │   │   │   │   └── di/           # Inyección de dependencias del feature
│   │   │   │   ├── team/             # Feature de gestión de equipos
│   │   │   │   │   ├── data/
│   │   │   │   │   ├── domain/
│   │   │   │   │   ├── presentation/
│   │   │   │   │   └── di/
│   │   │   │   └── settings/         # Feature de configuración
│   │   │   │       └── presentation/
│   │   │   ├── navigation/           # Navegación entre pantallas
│   │   │   ├── BottomNavigationBar.kt # Barra de navegación inferior
│   │   │   ├── CascaritaApp.kt       # Clase Application
│   │   │   └── MainActivity.kt       # Actividad principal
│   │   ├── res/                      # Recursos Android
│   │   │   ├── values/               # Strings, colores, temas
│   │   │   ├── values-night/         # Tema para modo oscuro
│   │   │   └── xml/                  # Reglas de backup
│   │   └── assets/                   # Assets adicionales
│   ├── src/test/                     # Tests unitarios
│   ├── build.gradle.kts              # Configuración de build del módulo
│   └── proguard-rules.pro            # Reglas de ofuscación ProGuard
├── gradle/                           # Configuración de Gradle
│   ├── libs.versions.toml            # Catálogo de versiones de dependencias
│   └── wrapper/                      # Wrapper de Gradle
├── build.gradle.kts                  # Configuración de build del proyecto
├── settings.gradle.kts               # Configuración de módulos
├── gradle.properties                 # Propiedades de Gradle
├── gradlew                           # Script de Gradle para Linux/Mac
└── gradlew.bat                       # Script de Gradle para Windows
```

## 🛠️ Stack Tecnológico

| Categoría | Tecnología |
|-----------|------------|
| **Lenguaje** | Kotlin 2.0.21 |
| **UI** | Jetpack Compose |
| **Arquitectura** | Clean Architecture + MVVM |
| **Inyección de Dependencias** | Hilt 2.51.1 |
| **Programación Asíncrona** | Kotlin Coroutines + Flow |
| **Base de Datos Local** | Room 2.6.1 |
| **Navegación** | Navigation Compose 2.7.7 |
| **Build System** | Gradle Kotlin DSL 8.7.3 |
| **Iconos** | Material Icons Extended |

## 🎨 Design System: "Neon Folklore"

### Paleta de Colores

| Color | Código | Uso |
|-------|--------|-----|
| **Primary** | `#FF88B2` | Rosa Mexicano - Acciones principales, botones |
| **Secondary** | `#5AF8FB` | Turquesa - Información técnica, detalles |
| **Tertiary** | `#FF9F4A` | Naranja - Acentos, botón "siguiente" |
| **Surface** | `#0E0E0E` | Fondo obsidiano - Fondos principales |

### Tipografía

| Fuente | Uso |
|--------|-----|
| **Noto Serif** | Scores, nombres de equipos, headers |
| **Manrope** | Textos de cuerpo, reglas, descripciones |

## 🚀 Cómo Construir el Proyecto

### Requisitos Previos

- Android Studio Hedgehog o superior
- JDK 17 o superior
- Android SDK API 26+ (Android 8.0)

### Pasos de Construcción

1. **Abrir el proyecto en Android Studio:**
   ```bash
   cd /workspace/App_Cascarita
   ```

2. **Sincronizar Gradle:**
   - Abrir Android Studio
   - Esperar a que se complete la sincronización automática
   - O ejecutar: `./gradlew --refresh-dependencies`

3. **Construir el proyecto:**
   ```bash
   ./gradlew build
   ```

4. **Ejecutar en emulador o dispositivo físico:**
   - Conectar dispositivo Android (API 26+) o iniciar emulador
   - Presionar el botón "Run" en Android Studio
   - O ejecutar: `./gradlew installDebug`

## 🧪 Testing

### Ejecutar Tests Unitarios
```bash
./gradlew test
```

### Ejecutar Tests con Reporte
```bash
./gradlew testDebugUnitTest
```

### Verificar Build Completo
```bash
./gradlew build && ./gradlew lint
```

## 📱 Características Principales

- ✅ **Gestión de Equipos**: Crear y administrar equipos de voleibol
- ✅ **Marcador en Vivo**: Seguimiento del score del partido
- ✅ **Configuración Personalizable**: Ajustar reglas y parámetros del juego
- ✅ **Modo Oscuro**: Diseño optimizado para modo oscuro
- ✅ **Persistencia Local**: Los datos se guardan en Room Database

## 📄 Licencia

Proyecto desarrollado como una aplicación casual para partidos de voleibol.

## 👨‍💻 Autoría

Basado en el código Python original por **Leonel Viloria (Tilichev®)**

## 📌 Versión Actual

**0.1.1** - Versión inicial con funcionalidades básicas de gestión de partidos de voleibol
