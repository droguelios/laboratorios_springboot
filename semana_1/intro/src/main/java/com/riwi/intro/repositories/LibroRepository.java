package com.riwi.intro.repositories;

import com.riwi.intro.models.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libros, Long>  {
}
