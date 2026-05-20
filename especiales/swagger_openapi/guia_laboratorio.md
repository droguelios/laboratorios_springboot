# 📖 Laboratorio Especial: Documentación Profesional con Swagger & OpenAPI 3

En la industria moderna, una API sin documentación es una API inexistente. Este laboratorio te enseñará a transformar tus endpoints en una interfaz interactiva y profesional siguiendo el estándar **OpenAPI 3**.

## 🎯 Objetivos de Aprendizaje

1.  Diferenciar entre las librerías antiguas (**Springfox**) y las modernas (**Springdoc**).
2.  Configurar **Springdoc OpenAPI** en una aplicación Spring Boot 3+.
3.  Personalizar metadatos, esquemas y respuestas para una documentación "Production-Ready".

---

## 📜 Contexto Histórico: Springfox vs. Springdoc

Antes de empezar, es vital entender el cambio tecnológico en el ecosistema:

- **Springfox (Legacy):** Fue el estándar durante años para Spring Boot 1.x y 2.x. Utilizaba la especificación Swagger 2. Sin embargo, su desarrollo se detuvo y no es compatible con Spring Boot 3.
- **Springdoc OpenAPI (Actual):** Es la librería recomendada hoy en día. Soporta la especificación **OpenAPI 3**, es totalmente compatible con Spring Boot 3 (Jakarta EE) y se actualiza constantemente.

---

## 🛠️ Parte 1: Configuración Inicial

Para Spring Boot 3+, solo necesitas una dependencia "paraguas" que incluye Swagger UI y los recursos de OpenAPI.

1.  Añadir al `pom.xml`:

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.5.0</version>
</dependency>
```

2.  Acceder a la interfaz (por defecto): `http://localhost:8080/swagger-ui.html`

---

## 🏗️ Parte 2: Personalización de Metadatos

No uses los valores por defecto. Define quién eres y qué hace tu API.

1.  Crear una clase de configuración `SwaggerConfig.java`:

```java
@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "API de Gestión de Eventify",
        version = "1.0",
        description = "Documentación detallada de los endpoints para la gestión de eventos y lugares.",
        contact = @Contact(name = "Soporte Riwi", email = "soporte@riwi.io")
    )
)
public class SwaggerConfig {
    // Aquí puedes añadir configuraciones adicionales como seguridad JWT
}
```

---

## 📝 Parte 3: Documentando el Controlador

Usa anotaciones para que los desarrolladores que consuman tu API entiendan cada método.

```java
@RestController
@RequestMapping("/api/events")
@Tag(name = "Eventos", description = "Operaciones relacionadas con la gestión de eventos")
public class EventController {

    @Operation(summary = "Obtener todos los eventos", description = "Retorna una lista paginada de todos los eventos registrados.")
    @ApiResponse(responseCode = "200", description = "Operación exitosa")
    @GetMapping
    public List<Event> getAll() { ... }

    @Operation(summary = "Crear un nuevo evento", description = "Registra un evento en el sistema y retorna el objeto creado.")
    @ApiResponse(responseCode = "201", description = "Evento creado correctamente")
    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    @PostMapping
    public Event create(@RequestBody Event event) { ... }
}
```

---

## 💎 Parte 4: Documentando el Modelo (Schemas)

Haz que tus modelos JSON sean descriptivos.

```java
@Data
@Schema(description = "Modelo que representa un evento en el sistema")
public class Event {
    @Schema(description = "ID único del evento", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nombre descriptivo del evento", example = "Concierto de Rock")
    private String name;

    @Schema(description = "Precio de la entrada", example = "45.50")
    private Double price;
}
```

---

## 🧪 Parte 5: Desafío del Día

**Reto "Doc-Professional":**

1.  Configura Swagger para que los endpoints se agrupen por "Módulos" (Eventos, Lugares, Usuarios).
2.  Añade una descripción personalizada a un parámetro de ruta (`@PathVariable`) usando `@Parameter`.
3.  Cambia la ruta por defecto de Swagger en el `application.properties` para que sea `/docs` en lugar de `/swagger-ui.html`.
    - _Pista:_ Busca `springdoc.swagger-ui.path`.

---

## 🔍 Temas de Investigación y Aprendizaje Continuo

Investiga estos conceptos clave para arquitectos de software:

1.  **API-First Design:** ¿Por qué muchas empresas escriben primero el archivo YAML de OpenAPI antes de escribir una sola línea de Java?
2.  **Swagger Hub:** Investiga las herramientas de colaboración para equipos grandes que usan OpenAPI.
3.  **Redoc:** Una alternativa visual a Swagger UI muy popular en la industria por su diseño limpio.

---

> [!IMPORTANT]
> Recuerda: La documentación es un contrato. Si tu Swagger dice que devuelves un campo `price`, pero tu código devuelve `costo`, estás rompiendo la confianza con tus clientes (front-end, mobile, otros servicios).
