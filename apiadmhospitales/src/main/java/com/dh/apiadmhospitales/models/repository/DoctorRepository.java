package com.dh.apiadmhospitales.models.repository;

import com.dh.apiadmhospitales.models.entity.Doctor;
import com.dh.apiadmhospitales.models.entity.Especialidad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {
    @Query("select e from Especialidad e where e.nombre like %?1%")
    List<Especialidad> findByNombre(String nombreEspecialidad);

    List<Especialidad> findByFechaCreacion(Date date);

    Page<Especialidad> findAll(Pageable pageable);
}
