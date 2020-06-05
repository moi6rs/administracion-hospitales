package com.dh.apiadmhospitales.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "especialidades")
public class Especialidad extends BaseEntity {

    @NotEmpty
    private String nombre;

    private String descripcion;

    @Lob
    @JsonIgnore
    private byte[] avatar;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAvatarHashCode() {
        return (this.avatar != null) ? this.avatar.hashCode() : null;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
