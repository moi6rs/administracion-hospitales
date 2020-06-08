package com.dh.apiadmhospitales.controllers;

import com.dh.apiadmhospitales.models.entity.Paciente;
import com.dh.apiadmhospitales.models.repository.PacienteRepository;
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
@RequestMapping("/api/pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository repository;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok().body(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> ver(@PathVariable Long id) {
        return ResponseEntity.ok().body(repository.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Paciente paciente, BindingResult result) {
        if (result.hasErrors()) {
            return new Validador().validar(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(paciente));
    }

    @PostMapping("/crear-con-foto")
    public ResponseEntity<?> crearConFoto(@Valid Paciente paciente, BindingResult result,
                                            @RequestParam MultipartFile archivo) throws IOException {
        if (result.hasErrors()) {
            return new Validador().validar(result);
        }
        if (!archivo.isEmpty()) {
            paciente.setFoto(archivo.getBytes());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(paciente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody Paciente paciente, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return new Validador().validar(result);
        }
        Optional<Paciente> pacienteOptional = repository.findById(id);
        ResponseEntity responseEntity = null;
        if (pacienteOptional.isPresent()) {
            Paciente pacienteDb = pacienteOptional.get();
            pacienteDb.setNombre(paciente.getNombre());
            pacienteDb.setApellido(paciente.getApellido());
            pacienteDb.setFechaNacimiento(paciente.getFechaNacimiento());
            pacienteDb.setDireccion(paciente.getDireccion());
            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(repository.save(pacienteDb));
        } else {
            responseEntity = ResponseEntity.notFound().build();
        }
        return responseEntity;
    }

    @PutMapping("/actualizar-con-foto/{id}")
    public ResponseEntity<?> actualizarConFoto(@Valid Paciente paciente, BindingResult result,
                                                 @PathVariable Long id, @RequestParam MultipartFile archivo) throws IOException {
        if (result.hasErrors()) {
            return new Validador().validar(result);
        }
        Optional<Paciente> pacienteOptional = repository.findById(id);
        ResponseEntity responseEntity = null;
        if (pacienteOptional.isPresent()) {
            Paciente pacienteDb = pacienteOptional.get();
            pacienteDb.setNombre(paciente.getNombre());
            pacienteDb.setApellido(paciente.getApellido());
            pacienteDb.setFechaNacimiento(paciente.getFechaNacimiento());
            pacienteDb.setDireccion(paciente.getDireccion());
            if (!archivo.isEmpty()) {
                pacienteDb.setFoto(archivo.getBytes());
            }
            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(repository.save(pacienteDb));
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
    public ResponseEntity<?> filtrarNombrePaciente(@PathVariable String nombre) {
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
