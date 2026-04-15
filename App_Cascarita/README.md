# Cascarita - App Android

## Estructura del Proyecto

```
app/
├── src/main/
│   ├── AndroidManifest.xml
│   ├── kotlin/com/cascarita/app/
│   │   ├── core/
│   │   │   ├── common/          # Utilidades y constantes
│   │   │   ├── di/              # Módulos Hilt
│   │   │   ├── database/        # Room Database
│   │   │   └── theme/           # Design System
│   │   ├── feature/
│   │   │   ├── game/            # Feature del juego
│   │   │   │   ├── data/
│   │   │   │   ├── domain/
│   │   │   │   ├── presentation/
│   │   │   │   └── di/
│   │   │   └── team/            # Feature de equipos
│   │   │       ├── data/
│   │   │       ├── domain/
│   │   │       ├── presentation/
│   │   │       └── di/
│   │   ├── navigation/          # Navegación
│   │   ├── CascaritaApp.kt      # Application class
│   │   └── MainActivity.kt      # Actividad principal
│   ├── res/
│   │   ├── values/              # Strings, colors, themes
│   │   ├── values-night/        # Modo oscuro
│   │   └── xml/                 # Backup rules
│   └── assets/
└── src/test/                    # Unit tests
```

## Stack Tecnológico

- **Lenguaje:** Kotlin 100%
- **UI:** Jetpack Compose
- **Arquitectura:** Clean Architecture + MVVM
- **DI:** Hilt
- **Async:** Kotlin Coroutines + Flow
- **Persistencia:** Room
- **Navegación:** Navigation Compose
- **Build:** Gradle Kotlin DSL

## Design System: "Neon Folklore"

### Colores
- **Primary (#FF88B2):** Rosa Mexicano - Acciones principales
- **Secondary (#5AF8FB):** Turquesa - Información técnica
- **Tertiary (#FF9F4A):** Naranja - Acentos y "siguiente"
- **Surface (#0E0E0E):** Fondo obsidiano

### Tipografía
- **Noto Serif:** Scores, nombres de equipos, headers
- **Manrope:** Textos de cuerpo, reglas

## Cómo Construir

1. Abrir el proyecto en Android Studio
2. Sincronizar Gradle
3. Ejecutar en emulador o dispositivo físico (API 26+)

```bash
./gradlew build
```

## Testing

```bash
./gradlew test
```

## Versión Actual

**0.1.1** - Basada en el código Python original por Leonel Viloria (Tilichev®)
