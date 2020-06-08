package com.dh.apiadmhospitales.controllers;

import com.dh.apiadmhospitales.models.entity.Especialidad;
import com.dh.apiadmhospitales.models.repository.EspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;
@RestController
@CrossOrigin
@RequestMapping("/api/especialidades")
public class EspecialidadController {
    @Autowired
    private EspecialidadRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<?> ver(@PathVariable Long id) {
        return ResponseEntity.ok().body(repository.findById(id));
    }

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

    @PostMapping("/crear-con-avatar")
    public ResponseEntity<?> crearConAvatar(@Valid Especialidad especialidad, BindingResult result,
                                            @RequestParam MultipartFile archivo) throws IOException {
        if (result.hasErrors()) {
            return new Validador().validar(result);
        }
        if (!archivo.isEmpty()) {
            especialidad.setAvatar(archivo.getBytes());
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
            especialidadDb.setDescripcion(especialidad.getDescripcion());
            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(repository.save(especialidadDb));
        } else {
            responseEntity = ResponseEntity.notFound().build();
        }
        return responseEntity;
    }

    @PutMapping("/actualizar-con-avatar/{id}")
    public ResponseEntity<?> actualizarConAvatar(@Valid Especialidad especialidad, BindingResult result,
                                                 @PathVariable Long id, @RequestParam MultipartFile archivo) throws IOException {
        if (result.hasErrors()) {
            return new Validador().validar(result);
        }
        Optional<Especialidad> especialidadOptional = repository.findById(id);
        ResponseEntity responseEntity = null;
        if (especialidadOptional.isPresent()) {
            Especialidad especialidadDb = especialidadOptional.get();
            especialidadDb.setNombre(especialidad.getNombre());
            especialidadDb.setDescripcion(especialidad.getDescripcion());
            if (!archivo.isEmpty()) {
                especialidadDb.setAvatar(archivo.getBytes());
            }
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
