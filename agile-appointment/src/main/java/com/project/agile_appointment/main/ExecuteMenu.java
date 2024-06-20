package com.project.agile_appointment.main;

import com.project.agile_appointment.service.AppointmentService;
import com.project.agile_appointment.service.PatientService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ExecuteMenu {

    private Scanner scanner = new Scanner(System.in);
    PatientService patientService;
    AppointmentService appointmentService;

    public void showMenu() {
        var menu = """
                1 - Cadastrar novo paciente
                2 - Marcação de consultas
                3 - Cancelamento de consultas
                0 - Sair                                 
                """;

        System.out.println(menu);
        var option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                registerNewUser();
                break;
            case 2:
                scheduleAppointment();
                break;
            case 3:
                cancelAppointment();
                break;
            case 0:
                System.out.println("Programa encerrado.");
                break;
            default:
                System.out.println("Opção inválida, tente novamente");
        }
    }

    public void registerNewUser() {

    }

    public void scheduleAppointment() {

    }

    public void cancelAppointment() {

    }


}


