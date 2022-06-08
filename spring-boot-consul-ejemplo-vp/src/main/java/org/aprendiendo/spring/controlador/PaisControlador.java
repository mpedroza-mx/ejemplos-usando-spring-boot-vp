package org.aprendiendo.spring.controlador;

import org.aprendiendo.spring.entidad.PaisEntidad;
import org.aprendiendo.spring.repositorio.PaisRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("paises")
public class PaisControlador {

  private final PaisRepositorio paisRepositorio;

  @Autowired
  public PaisControlador(PaisRepositorio paisRepositorio) {
    this.paisRepositorio = paisRepositorio;
  }

  @GetMapping
  public List<PaisEntidad> obtenerPaises(){
      return paisRepositorio.findAll();
  }
}
