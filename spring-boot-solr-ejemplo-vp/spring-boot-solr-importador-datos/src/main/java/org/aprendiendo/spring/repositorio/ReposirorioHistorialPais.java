package org.aprendiendo.spring.repositorio;

import org.aprendiendo.spring.modelo.HistorialPaisEliminado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;

@Repository
public interface ReposirorioHistorialPais extends JpaRepository<HistorialPaisEliminado,Long> {
    @Query(value = "SELECT * FROM historial_pais_eliminado p WHERE p.fecha_eliminacion > :ultimaEjecucion",
            nativeQuery = true)
    Collection<HistorialPaisEliminado> encontrarPaisesEliminados(LocalDateTime ultimaEjecucion);
}
