package com.project.agile_appointment;

import com.project.agile_appointment.main.ExecuteMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AgileAppointmentApplication implements CommandLineRunner {

    @Autowired
    private ExecuteMenu executeMenu;

    public static void main(String[] args) {
        SpringApplication.run(AgileAppointmentApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        executeMenu.showMenu();
    }

}
