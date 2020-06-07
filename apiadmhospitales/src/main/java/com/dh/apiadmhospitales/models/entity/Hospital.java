package com.dh.apiadmhospitales.models.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "hospitales")
public class Hospital extends BaseEntity {

    @NotEmpty
    private String nombreHospital;


    @NotEmpty
    private String direccion;

    public String getNombreHospital() {
        return nombreHospital;
    }

    public void setNombreHospital(String nombreHospital) {
        this.nombreHospital = nombreHospital;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
