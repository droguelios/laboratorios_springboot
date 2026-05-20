package com.riwi.intro.service;

import com.riwi.intro.models.Libros;
import com.riwi.intro.repositories.LibroRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class LibroService {
    
    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    // Paginación y Ordenamiento
    public Page<Libros> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("title").ascending());
        return libroRepository.findAll(pageable);
    }

    // Guardar libro nuevo
    public Libros saveLibro(Libros libro) {
        return libroRepository.save(libro);
    }

    // Obtener todos
    public List<Libros> getAll() {
        return libroRepository.findAll();
    }

    // Eliminar por ID
    public void delete(Long id) {
        libroRepository.deleteById(id);
    }

    // Buscar por ID
    public Libros findById(Long id) {
        return libroRepository.findById(id).orElse(null);
    }

    // ACTUALIZAR (Aquí está el arreglo del ID)
    public Libros update(Long id, Libros libro) {
        libro.setId(id); // Forzamos a que use el ID de la URL
        return libroRepository.save(libro);
    }
}
