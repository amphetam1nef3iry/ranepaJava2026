package org.example;

import org.example.presentation.ConsoleMenu;
import org.example.repository.EmployeeRepository;
import org.example.service.HRMService;

public class HrmApplication {
    public static void main(String[] args) {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        HRMService hrmService = new HRMService(employeeRepository);
        ConsoleMenu consoleMenu = new ConsoleMenu(employeeRepository, hrmService);

        consoleMenu.start();
    }
}