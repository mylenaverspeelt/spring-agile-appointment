package com.project.agile_appointment.repository;

import com.project.agile_appointment.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDateAndTime(LocalDateTime dateAndTime);
    List<Appointment> findByDateAndTimeAndSpecialty(LocalDateTime dataHora, String specialty);

}
