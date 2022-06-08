package org.aprendiendo.spring.entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "paises")
public class PaisEntidad {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nombre;

  public PaisEntidad(Long id, String nombre) {
    this.id = id;
    this.nombre = nombre;
  }

  public PaisEntidad(){

  }

  public Long getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }
}
