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
@Table(name = "historial_pais_eliminado")
public class HistorialPaisEliminado implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "pais_id")
    private Long paisId;
    private String nombre;
    private String capital;
    private Long poblacion;
    private String lenguaje;
    @Column(name = "fecha_actualizacion", insertable = false, updatable = false)
    private LocalDateTime fechaActualizacion;
    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    private LocalDateTime fechaCreacion;
    @Column(name = "fecha_eliminacion", insertable = false, updatable = false)
    private LocalDateTime fechaEliminacion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HistorialPaisEliminado that = (HistorialPaisEliminado) o;

        if (!id.equals(that.id)) return false;
        if (!paisId.equals(that.paisId)) return false;
        if (!nombre.equals(that.nombre)) return false;
        if (!capital.equals(that.capital)) return false;
        if (!poblacion.equals(that.poblacion)) return false;
        if (!lenguaje.equals(that.lenguaje)) return false;
        if (!fechaActualizacion.equals(that.fechaActualizacion)) return false;
        if (!fechaCreacion.equals(that.fechaCreacion)) return false;
        return fechaEliminacion.equals(that.fechaEliminacion);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + paisId.hashCode();
        result = 31 * result + nombre.hashCode();
        result = 31 * result + capital.hashCode();
        result = 31 * result + poblacion.hashCode();
        result = 31 * result + lenguaje.hashCode();
        result = 31 * result + fechaActualizacion.hashCode();
        result = 31 * result + fechaCreacion.hashCode();
        result = 31 * result + fechaEliminacion.hashCode();
        return result;
    }

    public Long getId() {
        return id;
    }

    public HistorialPaisEliminado setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getPaisId() {
        return paisId;
    }

    public HistorialPaisEliminado setPaisId(Long paisId) {
        this.paisId = paisId;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public HistorialPaisEliminado setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getCapital() {
        return capital;
    }

    public HistorialPaisEliminado setCapital(String capital) {
        this.capital = capital;
        return this;
    }

    public Long getPoblacion() {
        return poblacion;
    }

    public HistorialPaisEliminado setPoblacion(Long poblacion) {
        this.poblacion = poblacion;
        return this;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public HistorialPaisEliminado setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
        return this;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public HistorialPaisEliminado setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
        return this;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public HistorialPaisEliminado setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public LocalDateTime getFechaEliminacion() {
        return fechaEliminacion;
    }

    public HistorialPaisEliminado setFechaEliminacion(LocalDateTime fechaEliminacion) {
        this.fechaEliminacion = fechaEliminacion;
        return this;
    }
}
