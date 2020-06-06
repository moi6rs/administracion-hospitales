package com.dh.apiadmhospitales.services;

import com.dh.apiadmhospitales.models.dto.NotaDto;
import com.dh.apiadmhospitales.models.entity.Doctor;
import com.dh.apiadmhospitales.models.entity.Nota;
import com.dh.apiadmhospitales.models.entity.Paciente;
import com.dh.apiadmhospitales.models.repository.DoctorRepository;
import com.dh.apiadmhospitales.models.repository.NotaRepository;
import com.dh.apiadmhospitales.models.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotaService {
    @Autowired
    private NotaRepository repository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public Nota crearActualizarNota(NotaDto notaDto, Long id) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(notaDto.getDoctorId());
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(notaDto.getPacienteId());
        Nota nota;
        if (id == null) {
            nota = notaDto.toNota(new Nota());
            nota.setDoctor(doctorOptional.get());
            nota.setPaciente(pacienteOptional.get());
            repository.save(nota);
        } else {
            Optional<Nota> notaOptional = repository.findById(id);
            nota = notaDto.toNota(notaOptional.get());
            nota.setDoctor(doctorOptional.get());
            nota.setPaciente(pacienteOptional.get());
            repository.save(nota);
        }
        return  nota;
    }
}
