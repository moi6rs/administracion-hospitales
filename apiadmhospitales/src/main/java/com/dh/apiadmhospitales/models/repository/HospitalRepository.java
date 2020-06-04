package com.dh.apiadmhospitales.models.repository;

import com.dh.apiadmhospitales.models.entity.Hospital;
import org.springframework.data.repository.CrudRepository;

public interface HospitalRepository extends CrudRepository<Hospital,Long> {
}
