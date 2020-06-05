package com.dh.apiadmhospitales.controllers;

import com.dh.apiadmhospitales.models.entity.Especialidad;
import com.dh.apiadmhospitales.models.repository.EspecialidadRepository;
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
@RequestMapping("/especialidades")
public class EspecialidadController {
    @Autowired
    private EspecialidadRepository repository;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok().body(repository.findAll());
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Especialidad especialidad, BindingResult result) {
        if (result.hasErrors()) {
            return new Validador().validar(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(especialidad));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody Especialidad especialidad, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return new Validador().validar(result);
        }
        Optional<Especialidad> especialidadOptional = repository.findById(id);
        ResponseEntity responseEntity = null;
        if (especialidadOptional.isPresent()) {
            Especialidad especialidadDb = especialidadOptional.get();
            especialidadDb.setNombre(especialidad.getNombre());
            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(repository.save(especialidadDb));
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
    public ResponseEntity<?> filtrarNombreEspecialidad(@PathVariable String nombre) {
        return ResponseEntity.ok(repository.findByNombre(nombre));
    }

    @GetMapping("filtrar/fecha-creacion/{fecha}")
    public ResponseEntity<?> filtrarFechaCreacion(@PathVariable String fecha) throws ParseException {
        return ResponseEntity.ok(repository.findByFechaCreacion(new SimpleDateFormat("yyyy-MM-dd").parse(fecha)));
    }

    @GetMapping("/pagina")
    public ResponseEntity<?> listarPaginable(Pageable pageable) {
        return ResponseEntity.ok().body(repository.findAll(pageable));
    }
}
