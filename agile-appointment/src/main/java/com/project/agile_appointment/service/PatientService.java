package com.project.agile_appointment.service;

import com.project.agile_appointment.models.Patient;
import com.project.agile_appointment.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    public Patient registerNewPatient(String name, String phone) throws Exception {
        Optional<Patient> registeredUser = patientRepository.findByPhone(phone);

        if (registeredUser.isPresent()) {
            throw new Exception("Paciente já cadastrado. Tente novamente.");
        }

        Patient newPatient = new Patient();
        newPatient.setName(name);
        newPatient.setPhone(phone);
        return patientRepository.save(newPatient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public void deletePatient(Long id) throws Exception {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isPresent()) {
            patientRepository.delete(patient.get());
        } else {
            throw new Exception("Paciente não encontrado. Tente novamente.");
        }
    }
}
