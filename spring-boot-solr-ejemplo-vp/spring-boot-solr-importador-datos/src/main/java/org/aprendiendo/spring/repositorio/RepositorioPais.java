package org.aprendiendo.spring.repositorio;

import org.aprendiendo.spring.modelo.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;

@Repository
public interface RepositorioPais extends JpaRepository<Pais, Long> {

    @Query(value = "SELECT * FROM Pais p WHERE p.fecha_creacion > :ultimaEjecucion",
            nativeQuery = true)
    Collection<Pais> encontrarNuevosPaises(LocalDateTime ultimaEjecucion);

    @Query(value = "SELECT * FROM Pais p WHERE p.fecha_actualizacion > :ultimaEjecucion",
            nativeQuery = true)
    Collection<Pais> encontrarPaisesActualizados(LocalDateTime ultimaEjecucion);
}
