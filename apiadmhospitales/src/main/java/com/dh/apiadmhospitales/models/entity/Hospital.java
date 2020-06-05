package com.dh.apiadmhospitales.models.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "hospitales")
public class Hospital extends BaseEntity {

    @NotNull
    private String nombreHospital;

    public String getNombreHospital() {
        return nombreHospital;
    }

    public void setNombreHospital(String nombreHospital) {
        this.nombreHospital = nombreHospital;
    }
}
