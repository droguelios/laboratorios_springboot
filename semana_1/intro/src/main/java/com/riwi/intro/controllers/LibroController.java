package com.riwi.intro.controllers;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.riwi.intro.models.Libros;
import com.riwi.intro.service.LibroService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/libros")
@Tag(name = "Libros", description = "Operaciones relacionadas con la gestión de libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping("/paginated")
    @Operation(summary = "Obtener libros con paginación", description = "Permite listar libros divididos por páginas")
    public Page<Libros> getAllPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return this.libroService.findAll(page, size);
    }

    @Operation(summary = "Obtener todos los libros", description = "Retorna una lista de todos los libros registrados.")
    @ApiResponse(responseCode = "200", description = "Operación exitosa")
    @ApiResponse(responseCode = "404", description = "Libros no encontrados")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    @GetMapping
    public List<Libros> getAll() {
        return this.libroService.getAll();
    }

    @Operation(summary = "Crear un nuevo libro", description = "Registra un libro en el sistema y retorna el objeto creado.")
    @ApiResponse(responseCode = "201", description = "Libro creado correctamente")
    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    @PostMapping
    public Libros create(@RequestBody Libros libro) {
        return this.libroService.saveLibro(libro);
    }

    @Operation(summary = "Obtener un libro por su ID", description = "Busca un libro específico usando el ID")
    @GetMapping("/{id}")
    public Libros getByid(
            @Parameter(description = "ID del libro", example = "1") @PathVariable Long id) {
        return this.libroService.findById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un libro por su ID")
    public void delete(@PathVariable Long id) {
        this.libroService.delete(id);
    }
    
    @PutMapping("/update/{id}")
    @Operation(summary = "Actualizar un libro por su ID")
    public Libros update(@PathVariable Long id, @RequestBody Libros libro) {
        return this.libroService.update(id, libro);
    }
}
