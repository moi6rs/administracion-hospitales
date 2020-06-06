package com.dh.apiadmhospitales.models.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "pacientes")
public class Paciente extends Persona implements Serializable {
}
