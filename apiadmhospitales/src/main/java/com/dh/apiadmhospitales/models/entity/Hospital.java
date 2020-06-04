package com.dh.apiadmhospitales.models.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "hospitales")
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nombreHospital;
}
