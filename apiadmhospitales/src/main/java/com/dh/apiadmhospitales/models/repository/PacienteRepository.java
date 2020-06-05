package com.dh.apiadmhospitales.models.repository;

import com.dh.apiadmhospitales.models.entity.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface PacienteRepository extends CrudRepository<Paciente, Long> {
    @Query("select p from Paciente p where p.nombre like %?1%")
    List<Paciente> findByNombre(String nombrePaciente);

    List<Paciente> findByFechaCreacion(Date date);

    Page<Paciente> findAll(Pageable pageable);
}
