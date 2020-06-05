package com.dh.apiadmhospitales.models.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "especialidades")
public class Especialidad  extends BaseEntity{

    @NotEmpty
    private String nombre;
}
