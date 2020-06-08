package com.dh.apiadmhospitales.controllers;

import com.dh.apiadmhospitales.models.dto.NotaDto;
import com.dh.apiadmhospitales.models.entity.Doctor;
import com.dh.apiadmhospitales.models.entity.Nota;
import com.dh.apiadmhospitales.models.entity.Paciente;
import com.dh.apiadmhospitales.models.repository.DoctorRepository;
import com.dh.apiadmhospitales.models.repository.NotaRepository;
import com.dh.apiadmhospitales.models.repository.PacienteRepository;
import com.dh.apiadmhospitales.services.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/notas")
public class NotaController {
    @Autowired
    private NotaRepository repository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private NotaService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> ver(@PathVariable Long id) {
        return ResponseEntity.ok().body(repository.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> crearNota(@Valid @RequestBody NotaDto notaDto, BindingResult result) {

        if (result.hasErrors()) {
            return new Validador().validar(result);
        }

        ResponseEntity responseEntity;
        Optional<Doctor> doctorOptional = doctorRepository.findById(notaDto.getDoctorId());
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(notaDto.getPacienteId());

        if (doctorOptional.isPresent() && pacienteOptional.isPresent()) {
            Nota nota = service.crearActualizarNota(notaDto, null);
            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(nota);
        } else {
            responseEntity = ResponseEntity.notFound().build();
        }
        return responseEntity;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody NotaDto notaDto, @PathVariable Long id, BindingResult result) {
        if (result.hasErrors()) {
            return new Validador().validar(result);
        }
        Optional<Nota> notaOptional = repository.findById(id);
        ResponseEntity responseEntity;
        if (notaOptional.isPresent()) {
            Optional<Doctor> doctorOptional = doctorRepository.findById(notaDto.getDoctorId());
            Optional<Paciente> pacienteOptional = pacienteRepository.findById(notaDto.getPacienteId());
            if (doctorOptional.isPresent() && pacienteOptional.isPresent()) {
                Nota nota = service.crearActualizarNota(notaDto, id);
                responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(nota);
            } else {
                responseEntity = ResponseEntity.notFound().build();
            }
        } else {
            responseEntity = ResponseEntity.notFound().build();
        }
        return responseEntity;

    }
}
