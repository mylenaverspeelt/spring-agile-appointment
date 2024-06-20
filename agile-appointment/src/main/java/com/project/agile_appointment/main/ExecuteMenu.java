package com.project.agile_appointment.main;

import com.project.agile_appointment.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ExecuteMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final PatientService patientService;

    @Autowired
    public ExecuteMenu(PatientService patientService) {
        this.patientService = patientService;
    }
    public void showMenu() throws Exception {
        String menu = """
                1 - Cadastrar novo paciente
                2 - Marcação de consultas
                3 - Cancelamento de consultas
                0 - Sair                                 
                """;

        System.out.println(menu);
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                newPatient();
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

    public void newPatient() throws Exception {
        System.out.println("Informe o seu primeiro nome:");
        String name = scanner.nextLine();
        System.out.println("Informe o seu telefone para contato:");
        String phone = scanner.nextLine();

        try {
            var registeredPatient = patientService.registerNewPatient(name, phone);
            System.out.println("Paciente " + registeredPatient.getName() + " registrado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void scheduleAppointment() {
    }

    public void cancelAppointment() {
    }
}
