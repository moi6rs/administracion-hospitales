package com.dh.apiadmhospitales.controllers;

import com.dh.apiadmhospitales.models.entity.Hospital;
import com.dh.apiadmhospitales.models.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

@RestController
@RequestMapping("/api/hospitales")
public class HospitalController {
    @Autowired
    private HospitalRepository repository;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok().body(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> ver(@PathVariable Long id) {
        return ResponseEntity.ok().body(repository.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Hospital hospital, BindingResult result) {
        if (result.hasErrors()) {
            return new Validador().validar(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(hospital));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody Hospital hospital, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return new Validador().validar(result);
        }
        Optional<Hospital> hospitalOptional = repository.findById(id);
        ResponseEntity responseEntity = null;
        if (hospitalOptional.isPresent()) {
            Hospital hospitalDb = hospitalOptional.get();
            hospitalDb.setNombreHospital(hospital.getNombreHospital());
            hospitalDb.setDireccion(hospital.getDireccion());
            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(repository.save(hospitalDb));
        } else {
            responseEntity = ResponseEntity.notFound().build();
        }
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("filtrar/{nombre}")
    public ResponseEntity<?> filtrarNombreHospital(@PathVariable String nombre) {
        return ResponseEntity.ok(repository.findByNombreHospital(nombre));
    }

    @GetMapping("filtrar/fecha-creacion/{fecha}")
    public ResponseEntity<?> filtrarFechaCreacion(@PathVariable String fecha) throws ParseException {
        return ResponseEntity.ok(repository.findByFechaCreacion(new SimpleDateFormat("yyyy-MM-dd").parse(fecha)));
    }

    @GetMapping("/pagina")
    public ResponseEntity<?> listarPaginable(Pageable pageable){
        return ResponseEntity.ok().body(repository.findAll(pageable));
    }
}
