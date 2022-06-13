package org.aprendiendo.spring.modelo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "Pais")
public class Pais implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String capital;
    private Long poblacion;
    private String lenguaje;

    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    public LocalDateTime fechaCreacion;
    @Column(name = "fecha_actualizacion", insertable = false, updatable = false)
    public LocalDateTime fechaActualizacion;

    public Long getId() {
        return id;
    }

    public Pais setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public Pais setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getCapital() {
        return capital;
    }

    public Pais setCapital(String capital) {
        this.capital = capital;
        return this;
    }

    public Long getPoblacion() {
        return poblacion;
    }

    public Pais setPoblacion(Long poblacion) {
        this.poblacion = poblacion;
        return this;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public Pais setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
        return this;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public Pais setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public Pais setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pais pais = (Pais) o;

        if (!id.equals(pais.id)) return false;
        if (!nombre.equals(pais.nombre)) return false;
        if (!capital.equals(pais.capital)) return false;
        if (!poblacion.equals(pais.poblacion)) return false;
        if (!lenguaje.equals(pais.lenguaje)) return false;
        if (!fechaCreacion.equals(pais.fechaCreacion)) return false;
        return fechaActualizacion.equals(pais.fechaActualizacion);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + nombre.hashCode();
        result = 31 * result + capital.hashCode();
        result = 31 * result + poblacion.hashCode();
        result = 31 * result + lenguaje.hashCode();
        result = 31 * result + fechaCreacion.hashCode();
        result = 31 * result + fechaActualizacion.hashCode();
        return result;
    }
}
