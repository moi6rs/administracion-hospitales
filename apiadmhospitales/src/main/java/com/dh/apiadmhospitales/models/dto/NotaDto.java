package com.dh.apiadmhospitales.models.dto;

import com.dh.apiadmhospitales.models.entity.Nota;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class NotaDto {
    @NotEmpty
    private String descripcion;
    @NotNull
    private Date fecha;
    @NotNull
    private Long doctorId;

    @NotNull
    private Long pacienteId;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Nota toNota(Nota nota)
    {
        nota.setDescripcion(descripcion);
        nota.setFecha(fecha);
        return nota;
    }
}
