package org.example.presentation;

import org.example.model.Employee;
import org.example.repository.EmployeeRepository;
import org.example.service.HRMService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {
    private final EmployeeRepository employeeRepository;
    private final HRMService hrmService;
    private final Scanner scanner;

    public ConsoleMenu(EmployeeRepository employeeRepository, HRMService hrmService) {
        this.employeeRepository = employeeRepository;
        this.hrmService = hrmService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\n=== HRM System Menu ===");
            System.out.println("1. Show all employees");
            System.out.println("2. Add employee");
            System.out.println("3. Delete employee");
            System.out.println("4. Find employee by ID");
            System.out.println("5. Show statistics");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            try {
                int option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    case 1:
                        showAllEmployees();
                        break;
                    case 2:
                        addEmployee();
                        break;
                    case 3:
                        deleteEmployee();
                        break;
                    case 4:
                        findEmployeeById();
                        break;
                    case 5:
                        showStatistics();
                        break;
                    case 6:
                        System.out.println("Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid option");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number");
            }
        }
    }

    private void showAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        if (employees.isEmpty()) {
            System.out.println("No employees found");
            return;
        }

        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    private void addEmployee() {
        try {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();

            System.out.print("Enter position: ");
            String position = scanner.nextLine();

            System.out.print("Enter salary: ");
            double salary = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter hire date (YYYY-MM-DD): ");
            LocalDate hireDate = LocalDate.parse(scanner.nextLine());

            Employee employee = new Employee(name, position, salary, hireDate);
            employeeRepository.save(employee);

            System.out.println("Employee added successfully with ID: " + employee.getId());
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }

    private void deleteEmployee() {
        try {
            System.out.print("Enter employee ID: ");
            Long id = Long.parseLong(scanner.nextLine());

            Employee employee = employeeRepository.findById(id);

            if (employee == null) {
                System.out.println("Employee not found");
                return;
            }

            employeeRepository.delete(id);
            System.out.println("Employee deleted successfully");
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID");
        }
    }

    private void findEmployeeById() {
        try {
            System.out.print("Enter employee ID: ");
            Long id = Long.parseLong(scanner.nextLine());

            Employee employee = employeeRepository.findById(id);

            if (employee == null) {
                System.out.println("Employee not found");
            } else {
                System.out.println(employee);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID");
        }
    }

    private void showStatistics() {
        double averageSalary = hrmService.calculateAverageSalary();
        Employee topEmployee = hrmService.findTopPaidEmployee();

        System.out.println("Average salary: " + averageSalary);

        if (topEmployee == null) {
            System.out.println("Top paid employee: not found");
        } else {
            System.out.println("Top paid employee: " + topEmployee);
        }
    }
}