package com.project.agile_appointment.main;

import com.project.agile_appointment.models.Appointment;
import com.project.agile_appointment.models.Patient;
import com.project.agile_appointment.repository.AppointmentRepository;
import com.project.agile_appointment.service.AppointmentService;
import com.project.agile_appointment.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Component
public class ExecuteMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final PatientService patientService;
    private final AppointmentRepository appointmentRepository;
    private final AppointmentService appointmentService;

    @Autowired
    public ExecuteMenu(PatientService patientService, AppointmentRepository appointmentRepository, AppointmentService appointmentService) {
        this.patientService = patientService;
        this.appointmentRepository = appointmentRepository;
        this.appointmentService = appointmentService;
    }

    public void showMenu() throws Exception {
        String menu = """
                ***************************************
                1 - Cadastrar novo paciente
                2 - Marcação de consultas
                3 - Cancelamento de consultas
                0 - Sair                               
                ***************************************  
                """;

        System.out.println(menu);
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                newPatient();
                break;
            case 2:
                schedule();
                break;
            case 3:
                cancel();
                break;
            case 0:
                System.out.println("Programa encerrado.");
                break;
            default:
                System.out.println("Opção inválida, tente novamente");
                showMenu();
        }
    }

    public void newPatient() throws Exception {
        System.out.println("Informe o seu primeiro nome:");
        String name = scanner.nextLine();
        System.out.println("Informe o seu telefone para contato:");
        String phone = scanner.nextLine();

        try {
            var registeredPatient = patientService.registerNewPatient(name, phone);
            System.out.println("Paciente " + registeredPatient.getName() + " registrado com sucesso!");
            showMenu();
        } catch (Exception e) {
            System.out.println("Erro ao criar novo registro: " + e.getMessage());
        } finally {
            showMenu();
        }
    }

    public void schedule() throws Exception {

        List<Patient> patientsList = patientService.getAllPatients();

        if (patientsList.isEmpty()) {
            System.out.println("Nenhum paciente registrado. Realize o cadastro do paciente na opçao 1 do menu.");
            showMenu();
        }
        System.out.println("Pacientes cadastrados:");
        for (int i = 0; i < patientsList.size(); i++) {
            System.out.println((i + 1) + " - " + patientsList.get(i).getName());
        }

        System.out.println("Informe para qual paciente deseja realizar a marcação:");
        int patientIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        Patient selectedPatient = patientsList.get(patientIndex);

        System.out.println("Informe a data da consulta (formato: DD-MM-AAA):");
        String dateInput = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        System.out.println("Informe o horário da consulta (formato: HH:MM):");
        String timeInput = scanner.nextLine();
        LocalTime time = LocalTime.parse(timeInput);

        System.out.println("Informe a especialidade da consulta:");
        String specialty = scanner.nextLine();

        try {
            var newAppointment = appointmentService.scheduleAppointment(selectedPatient.getId(), date, time, specialty);
            var formattedDate = newAppointment.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            System.out.println("Consulta agendada com sucesso para o dia " + formattedDate + " às " + newAppointment.getTime() + " para a especialidade " + newAppointment.getSpecialty());
        } catch (Exception e) {
            System.out.println("Erro ao agendar consulta: " + e.getMessage());
        } finally {
            showMenu();
        }

        if (patientIndex == 0 || patientIndex >= patientsList.size()) {
            System.out.println("Opção inválida, tente novamente");
            schedule();
        }
    }

    public void cancel() throws Exception {

        List<Appointment> appointmentsList = appointmentService.getAllAppointments();

        if (appointmentsList.isEmpty()) {
            System.out.println("Nenhuma consulta marcada. Realize a marcação na opção 2 do menu.");
            showMenu();
        }

        System.out.println("Consultas Agendadas:");
        for (int i = 0; i < appointmentsList.size(); i++) {
            var formattedDate = appointmentsList.get(i).getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            System.out.println((i + 1) + " - " + appointmentsList.get(i).getSpecialty() + " - " + appointmentsList.get(i).getPatient().getName() + " - " + formattedDate + " - " + appointmentsList.get(i).getTime());
        }

        System.out.println("Informe qual consulta você deseja cancelar:");
        int appointmentIndex = scanner.nextInt();
        scanner.nextLine();

        if (appointmentIndex < 1 || appointmentIndex > appointmentsList.size()) {
            System.out.println("Opção inválida, tente novamente");
            cancel();
        }

        Appointment selectedAppointment = appointmentsList.get(appointmentIndex -1);

        try {
            appointmentService.cancelAppointment(selectedAppointment.getId());
            System.out.println("Consulta cancelada com sucesso!");
            showMenu();
        } catch (Exception e) {
            System.out.println("Erro ao cancelar consulta: " + e.getMessage());
        } finally {
            showMenu();
        }
    }

}