package org.aprendiendo.spring.repositorio;

import org.aprendiendo.spring.entidad.PaisEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaisRepositorio extends JpaRepository<PaisEntidad, Long> {

}
