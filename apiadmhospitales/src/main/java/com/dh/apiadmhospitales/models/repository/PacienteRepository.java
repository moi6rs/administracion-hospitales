package com.dh.apiadmhospitales.models.repository;

import com.dh.apiadmhospitales.models.entity.Paciente;
import org.springframework.data.repository.CrudRepository;

public interface PacienteRepository extends CrudRepository<Paciente, Long> {
}
