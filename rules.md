# 📜 rules.md - Estructura de Carpetas para App Android
> **Objetivo:** Generar una arquitectura limpia, escalable y lista para desarrollo. El agente debe crear carpetas, paquetes y archivos base respetando estrictamente las reglas de propósito, contenido y separación de responsabilidades.

---

## 🛠️ Stack y Convenciones Base
- **Lenguaje:** Kotlin (100%)
- **UI:** Jetpack Compose
- **Arquitectura:** Clean Architecture + MVVM
- **DI:** Hilt
- **Async:** Kotlin Coroutines + Flow
- **Persistencia:** Room
- **Red:** Retrofit + Kotlinx Serialization
- **Navegación:** Navigation Compose
- **Build:** Gradle Kotlin DSL (`.kts`)
- **Paquete base:** `com.tupackage` (reemplazar por el real)

---

## 🌳 Estructura Requerida
```tree
app/
└── src/main/
    ├── AndroidManifest.xml
    ├── kotlin/com/tupackage/
    │   ├── core/
    │   │   ├── common/
    │   │   ├── di/
    │   │   ├── network/
    │   │   └── database/
    │   ├── feature/
    │   │   └── <feature_name>/
    │   │       ├── data/
    │   │       ├── domain/
    │   │       └── presentation/
    │   └── App.kt
    ├── res/
    │   ├── drawable/
    │   ├── menu/
    │   ├── mipmap-anydpi-v26/
    │   ├── mipmap-hdpi/ ... (densidades)
    │   ├── navigation/
    │   ├── values/
    │   ├── values-night/
    │   └── xml/
    └── assets/

📖 Reglas Detalladas por Carpeta
    📂 kotlin/com/tupackage/core/
        🎯 Propósito: Código transversal, configuración base y utilidades reutilizables en toda la app.
        📦 Contenido esperado:
        common/: Extensiones de Kotlin, Result<T>, Resource<T>, validadores, formateadores, constantes globales.
        di/: Módulos Hilt (NetworkModule.kt, DatabaseModule.kt, RepositoryModule.kt). Solo @Provides y @Module.
        network/: Retrofit client, interceptores (auth, logging, timeouts), serialización, manejo de respuestas HTTP.
        database/: AppDatabase.kt (Room), TypeConverter, migraciones, DAOs base genéricos.
        ⚠️ Reglas:
        ✅ NO contener UI, ViewModels, ni lógica de negocio específica.
        ✅ Dependencias: core puede ser importado por feature, pero core NUNCA importa feature.
        ✅ Clases de infraestructura deben estar inyectadas vía Hilt.
    📂 kotlin/com/tupackage/feature/<feature_name>/
        🎯 Propósito: Módulo autosuficiente por funcionalidad. Reemplazar <feature_name> por: login, home, profile, etc.
        📦 Subcarpetas y flujo de datos:
        data/: Implementaciones concretas de repositorios, DataSources (Retrofit services, Room DAOs), mappers DTO → Domain, SharedPreferences.
        domain/: Entidades puras (data class), interfaces de repositorio (interface UserRepository), casos de uso (GetUserUseCase, LoginUserUseCase).
        presentation/: ViewModel, pantallas @Composable, UiState, navegación interna, componentes UI exclusivos de la feature.
        ⚠️ Reglas:
        ✅ Flujo estricto: Presentation → Domain → Data → APIs/DB
        ✅ presentation depende de domain. domain NO depende de nada. data implementa domain.
        🚫 Prohibido acceso directo desde presentation a Retrofit, Room o Context de infraestructura.
        ✅ Nombres estándar: FeatureScreen.kt, FeatureViewModel.kt, FeatureRepository.kt, GetFeatureDataUseCase.kt
    📂 kotlin/com/tupackage/App.kt
        🎯 Propósito: Clase Application. Punto de entrada de Hilt y configuración global.
        📦 Contenido: @HiltAndroidApp, inicialización de librerías globales (Timber, Crashlytics, etc.).
        ⚠️ Reglas:
        ✅ Debe declararse en AndroidManifest.xml como android:name=".App"
        ❌ No contener lógica de negocio, navegación ni instanciación manual de dependencias.
    📂 res/
        🎯 Propósito: Recursos compilados por Android (generan R.*).
        📦 Uso por carpeta:
        drawable/: Vector Drawables, shapes, selectores, fondos.
        mipmap-*/: Solo iconos de lanzador. Mantener todas las densidades requeridas.
        values/: strings.xml, colors.xml, themes.xml, dimens.xml.
        values-night/: Overrides para modo oscuro.
        navigation/: nav_graph.xml (si se usa) o rutas de navegación.
        xml/: provider_paths.xml, backup_rules.xml, network_security_config.xml.
        ⚠️ Reglas:
        ✅ Nombres en snake_case (ic_arrow_back.xml, btn_primary.xml)
        🚫 No hardcodear strings, colores o dimensiones en código Kotlin.
        🚫 No colocar lógica, código o assets no compilables aquí.
    📂 assets/
        🎯 Propósito: Archivos en crudo que NO se compilan ni optimizan.
        📦 Contenido: JSON estáticos, fuentes .ttf/.otf (si no van en res/font/), PDFs, licencias, DB prellenadas.
        ⚠️ Reglas: ✅ Acceder vía AssetManager. ❌ No confundir con res/raw/ (que sí se compila y optimiza).
        📂 test/ y androidTest/
        🎯 Propósito: Pruebas automatizadas.
        📦 Contenido:
        test/: Unit tests JVM (JUnit 5, MockK/Kluent). Probar ViewModels, UseCases, Repositorios mockeados, Mappers.
        androidTest/: Tests instrumentados (Espresso/Compose Testing). Probar UI, integración Room/Retrofit, flujos completos.
        ⚠️ Reglas:
        ✅ Replicar exactamente la estructura de main/kotlin/ para localización rápida.
        ✅ Nombrar tests como FeatureViewModelTest.kt, GetUserUseCaseTest.kt.
        🚫 No mezclar lógica de producción en estas carpetas.
⚙️ Convenciones Globales Obligatorias
    Paquetes: com.tupackage.core.network, com.tupackage.feature.login.domain
    Inmutabilidad: Preferir val, data class, sealed interface para estados y respuestas.
    Separación estricta: domain/ NUNCA importa androidx.* (excepto androidx.annotation).
    Hilt: Usar @Inject, @Provides, @Module, @InstallIn. Nunca new ni Object() de clases inyectadas.
    Compose: UI pura. Side effects solo en LaunchedEffect/DisposableEffect. ViewModel expone StateFlow.
    Manifiesto: Declarar solo componentes públicos. Permisos mínimos requeridos. exported explícito en Activities/Receivers.
    ✅ Checklist de Autovalidación (Ejecutar antes de entregar)
    domain/ no tiene imports de data/ ni androidx.*
    presentation/ no accede directamente a APIs, DB o Context de infraestructura
    Cada feature contiene data/, domain/, presentation/
    res/values/strings.xml existe y está vacío o con placeholder
    AndroidManifest.xml referencia .App correctamente
    test/ y androidTest/ replican estructura de main/
    No hay carpetas vacías, ni archivos .tmp/.bak/.DS_Store
    Todos los nombres de paquetes siguen com.tupackage.<capa>.<módulo>
    🚀 Instrucción Final para el Agente
    Crea la estructura de carpetas exactamente como se define en 🌳 Estructura Requerida.
    Dentro de cada paquete, genera archivos .kt con nombres estándar y un comentario // TODO: Implementar <responsabilidad> en el cuerpo.
    En res/, crea los archivos XML base (strings.xml, colors.xml, themes.xml, AndroidManifest.xml) con estructura mínima válida.
    Valida contra el ✅ Checklist de Autovalidación. Si falla algún punto, corrige antes de finalizar.
    Entrega solo la estructura y los archivos base. No generes lógica de negocio, llamadas a red ni UI completa en esta fase.