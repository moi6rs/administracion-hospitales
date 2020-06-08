package com.dh.apiadmhospitales.controllers;

import com.dh.apiadmhospitales.models.entity.Doctor;
import com.dh.apiadmhospitales.models.entity.Especialidad;
import com.dh.apiadmhospitales.models.entity.Paciente;
import com.dh.apiadmhospitales.models.repository.DoctorRepository;
import com.dh.apiadmhospitales.models.repository.EspecialidadRepository;
import com.dh.apiadmhospitales.models.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/documentos")
public class DocumentoController {
    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping("/especialidad/img/{id}")
    public ResponseEntity<?> verAvatarEspecialidad(@PathVariable Long id) {
        Optional<Especialidad> optionalEspecialidad = especialidadRepository.findById(id);

        if (!optionalEspecialidad.isPresent() || optionalEspecialidad.get().getAvatar() == null) {
            return ResponseEntity.notFound().build();
        }

        Resource imagen = new ByteArrayResource(optionalEspecialidad.get().getAvatar());

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);
    }

    @GetMapping("/paciente/img/{id}")
    public ResponseEntity<?> verFotoPaciente(@PathVariable Long id) {
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);

        if (!optionalPaciente.isPresent() || optionalPaciente.get().getFoto() == null) {
            return ResponseEntity.notFound().build();
        }

        Resource imagen = new ByteArrayResource(optionalPaciente.get().getFoto());

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);
    }

    @GetMapping("/doctor/img/{id}")
    public ResponseEntity<?> verFotoDoctor(@PathVariable Long id) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);

        if (!optionalDoctor.isPresent() || optionalDoctor.get().getFoto() == null) {
            return ResponseEntity.notFound().build();
        }

        Resource imagen = new ByteArrayResource(optionalDoctor.get().getFoto());

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);
    }
}
