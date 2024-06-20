package com.project.agile_appointment.repository;

import com.project.agile_appointment.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDateAndTime(LocalDate date, LocalTime time);
    List<Appointment> findByDateAndTimeAndSpecialty(LocalDate date, LocalTime time, String specialty);

}
