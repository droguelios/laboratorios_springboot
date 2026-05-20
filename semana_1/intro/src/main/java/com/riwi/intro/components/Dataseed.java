package com.riwi.intro.components;

import com.riwi.intro.models.Libros;
import com.riwi.intro.repositories.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Dataseed implements CommandLineRunner {

    @Autowired
    private LibroRepository libroRepository;

    @Override
    public void run(String... args) throws Exception {
        if (libroRepository.count() == 0) {
            for (int i = 1; i <= 50; i++) {
                libroRepository.save(new Libros(null, "Libro de Prueba " + i, "Autor " + (i % 5), "ISBN-" + i, 2020 + (i % 5)));
            }
            System.out.println("✅ Datos de prueba insertados.");
        }
    }
}
