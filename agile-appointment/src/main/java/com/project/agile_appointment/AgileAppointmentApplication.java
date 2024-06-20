package com.project.agile_appointment;

import com.project.agile_appointment.main.ExecuteMenu;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AgileAppointmentApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AgileAppointmentApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ExecuteMenu execute = new ExecuteMenu();
        execute.showMenu();
    }

}
