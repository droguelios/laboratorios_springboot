package com.riwi.intro.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "libros")
public class Libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del libro", example = "1")
    private Long id;

    @Schema(description = "Título del libro", example = "Cien años de soledad")
    private String title;

    @Schema(description = "Nombre del autor", example = "Gabriel García Márquez")
    private String author;

    @Schema(description = "Código ISBN único", example = "978-0307474728")
    private String isbn;

    @Schema(description = "Año en que fue publicado", example = "1967")
    private int anioPublicacion;
}
