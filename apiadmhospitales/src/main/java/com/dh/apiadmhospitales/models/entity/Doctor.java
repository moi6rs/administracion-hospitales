package com.dh.apiadmhospitales.models.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "doctores")
public class Doctor extends Persona implements Serializable {
}
