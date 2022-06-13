package org.aprendiendo.spring.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;
import java.util.Date;

@JsonInclude(Include.NON_NULL)
public class PaisDto implements Serializable {
  @Field
  private String id;
  @Field
  private String nombre;
  @Field
  private String capital;
  @Field
  private Long poblacion;
  @Field
  private String lenguaje;
  @Field
  private Date fechaActualizacion;
  @Field
  private Date fechaCreacion;

  public String getId() {
    return id;
  }

  public PaisDto setId(String id) {
    this.id = id;
    return this;
  }

  public String getNombre() {
    return nombre;
  }

  public PaisDto setNombre(String nombre) {
    this.nombre = nombre;
    return this;
  }

  public String getCapital() {
    return capital;
  }

  public PaisDto setCapital(String capital) {
    this.capital = capital;
    return this;
  }

  public Long getPoblacion() {
    return poblacion;
  }

  public PaisDto setPoblacion(Long poblacion) {
    this.poblacion = poblacion;
    return this;
  }

  public String getLenguaje() {
    return lenguaje;
  }

  public PaisDto setLenguaje(String lenguaje) {
    this.lenguaje = lenguaje;
    return this;
  }

  public Date getFechaActualizacion() {
    return fechaActualizacion;
  }

  public PaisDto setFechaActualizacion(Date fechaActualizacion) {
    this.fechaActualizacion = fechaActualizacion;
    return this;
  }

  public Date getFechaCreacion() {
    return fechaCreacion;
  }

  public PaisDto setFechaCreacion(Date fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PaisDto paisDto = (PaisDto) o;

    if (!id.equals(paisDto.id)) return false;
    if (!nombre.equals(paisDto.nombre)) return false;
    if (!capital.equals(paisDto.capital)) return false;
    if (!poblacion.equals(paisDto.poblacion)) return false;
    if (!lenguaje.equals(paisDto.lenguaje)) return false;
    if (!fechaActualizacion.equals(paisDto.fechaActualizacion)) return false;
    return fechaCreacion.equals(paisDto.fechaCreacion);
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + nombre.hashCode();
    result = 31 * result + capital.hashCode();
    result = 31 * result + poblacion.hashCode();
    result = 31 * result + lenguaje.hashCode();
    result = 31 * result + fechaActualizacion.hashCode();
    result = 31 * result + fechaCreacion.hashCode();
    return result;
  }
}
