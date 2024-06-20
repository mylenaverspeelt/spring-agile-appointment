package com.project.agile_appointment.service;

import com.project.agile_appointment.models.Appointment;
import com.project.agile_appointment.models.Patient;
import com.project.agile_appointment.repository.AppointmentRepository;
import com.project.agile_appointment.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
    }

    public Appointment scheduleAppointment(Long patientId, LocalDate date, LocalTime time, String specialty) throws Exception {

        if (date.isBefore(LocalDate.now()) && time.isBefore(LocalTime.now())) {
            throw new IllegalArgumentException("Consultas não podem ser marcadas antes da data atual.");
        }
        List<Appointment> sameTimeSchedule = appointmentRepository.findByDateAndTime(date, time);
        if (!sameTimeSchedule.isEmpty()) {
            throw new Exception("Já existe uma consulta agendada para essa data e horário. Tente novamente.");
        }

        Optional<Patient> registeredPatient = patientRepository.findById(patientId);
        if (registeredPatient.isEmpty()) {
            throw new Exception("Paciente não encontrado.");
        }

        if (!sameTimeSchedule.isEmpty()) {
            throw new Exception("Já existe uma consulta agendada para essa data e horário.");
        }

        Patient patient = registeredPatient.get();
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDate(date);
        appointment.setTime(time);
        appointment.setSpecialty(specialty);

        return appointmentRepository.save(appointment);
    }

    public void cancelAppointment(Long appointmentId) throws Exception {

        Optional<Appointment> scheduledAppointment = appointmentRepository.findById(appointmentId);

        if (scheduledAppointment.isEmpty()) {
            throw new Exception("Consulta não encontrada.");
        }

        appointmentRepository.delete(scheduledAppointment.get());
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }


}
