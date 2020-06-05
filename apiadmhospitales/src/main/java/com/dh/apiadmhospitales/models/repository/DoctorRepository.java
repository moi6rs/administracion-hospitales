package com.dh.apiadmhospitales.models.repository;

import com.dh.apiadmhospitales.models.entity.Doctor;
import org.springframework.data.repository.CrudRepository;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {
}
