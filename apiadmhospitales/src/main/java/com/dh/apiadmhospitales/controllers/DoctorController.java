package com.dh.apiadmhospitales.controllers;

import com.dh.apiadmhospitales.models.entity.Doctor;
import com.dh.apiadmhospitales.models.repository.DoctorRepository;
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
@RequestMapping("/doctores")
public class DoctorController {
    @Autowired
    private DoctorRepository repository;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok().body(repository.findAll());
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Doctor doctor, BindingResult result) {
        if (result.hasErrors()) {
            return new Validador().validar(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(doctor));
    }

    @PostMapping("/crear-con-foto")
    public ResponseEntity<?> crearConFoto(@Valid Doctor doctor, BindingResult result,
                                          @RequestParam MultipartFile archivo) throws IOException {
        if (result.hasErrors()) {
            return new Validador().validar(result);
        }
        if (!archivo.isEmpty()) {
            doctor.setFoto(archivo.getBytes());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(doctor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody Doctor doctor, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return new Validador().validar(result);
        }
        Optional<Doctor> doctorOptional = repository.findById(id);
        ResponseEntity responseEntity = null;
        if (doctorOptional.isPresent()) {
            Doctor doctorDb = doctorOptional.get();
            doctorDb.setNombre(doctor.getNombre());
            doctorDb.setApellido(doctor.getApellido());
            doctorDb.setFechaNacimiento(doctor.getFechaNacimiento());
            doctorDb.setDireccion(doctor.getDireccion());
            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(repository.save(doctorDb));
        } else {
            responseEntity = ResponseEntity.notFound().build();
        }
        return responseEntity;
    }

    @PutMapping("/actualizar-con-foto/{id}")
    public ResponseEntity<?> actualizarConFoto(@Valid Doctor doctor, BindingResult result,
                                               @PathVariable Long id, @RequestParam MultipartFile archivo) throws IOException {
        if (result.hasErrors()) {
            return new Validador().validar(result);
        }
        Optional<Doctor> doctorOptional = repository.findById(id);
        ResponseEntity responseEntity = null;
        if (doctorOptional.isPresent()) {
            Doctor doctorDb = doctorOptional.get();
            doctorDb.setNombre(doctor.getNombre());
            doctorDb.setApellido(doctor.getApellido());
            doctorDb.setFechaNacimiento(doctor.getFechaNacimiento());
            doctorDb.setDireccion(doctor.getDireccion());
            if (!archivo.isEmpty()) {
                doctorDb.setFoto(archivo.getBytes());
            }
            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(repository.save(doctorDb));
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
    public ResponseEntity<?> filtrarNombreDoctor(@PathVariable String nombre) {
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

    @GetMapping("uploads/img/{id}")
    public ResponseEntity<?> verFoto(@PathVariable Long id) {
        Optional<Doctor> optionalDoctor = repository.findById(id);

        if (!optionalDoctor.isPresent() || optionalDoctor.get().getFoto() == null) {
            return ResponseEntity.notFound().build();
        }

        Resource imagen = new ByteArrayResource(optionalDoctor.get().getFoto());

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);
    }
}
