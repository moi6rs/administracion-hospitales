package com.dh.apiadmhospitales.controllers;

import com.dh.apiadmhospitales.models.entity.Hospital;
import com.dh.apiadmhospitales.models.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/hospitales")
public class HospitalController {
    @Autowired
    private HospitalRepository repository;

    @GetMapping
    public ResponseEntity<?> findAll(){
        return  ResponseEntity.ok().body(repository.findAll());
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Hospital hospital){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(hospital));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@RequestBody Hospital hospital, @PathVariable Long id){
        Optional<Hospital> hospitalOptional = repository.findById(id);
        ResponseEntity responseEntity = null;
        if(hospitalOptional.isPresent()){
            Hospital hospitalDb = hospitalOptional.get();
            hospitalDb.setNombreHospital(hospital.getNombreHospital());
            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(repository.save(hospitalDb));
        }else{
            responseEntity = ResponseEntity.notFound().build();
        }
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
