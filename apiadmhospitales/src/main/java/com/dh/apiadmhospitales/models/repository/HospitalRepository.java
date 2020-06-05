package com.dh.apiadmhospitales.models.repository;

import com.dh.apiadmhospitales.models.entity.Hospital;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface HospitalRepository extends CrudRepository<Hospital,Long> {

    @Query("select h from Hospital h where h.nombreHospital like %?1%")
    public List<Hospital> findByNombreHospital(String nombreHospital);

    public List<Hospital> findByFechaCreacion(Date date);
}
