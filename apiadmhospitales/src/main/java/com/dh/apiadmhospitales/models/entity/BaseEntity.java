package com.dh.apiadmhospitales.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_actualizacion")
    private Date fechaActualizacion;

    @JsonIgnore
    @Column(name = "creado_por")
    private Long creadoPor;

    @JsonIgnore
    @Column(name = "actualizado_por")
    private Long actualizadoPor;

    @PrePersist
    public void prePersist() {
        fechaCreacion = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        fechaActualizacion = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Long getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Long creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Long getActualizadoPor() {
        return actualizadoPor;
    }

    public void setActualizadoPor(Long actualizadoPor) {
        this.actualizadoPor = actualizadoPor;
    }
}
