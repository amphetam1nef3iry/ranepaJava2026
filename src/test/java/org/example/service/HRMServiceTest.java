package org.example.service;

import org.example.model.Employee;
import org.example.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class HRMServiceTest {

    private EmployeeRepository employeeRepository;
    private HRMService hrmService;

    @BeforeEach
    void setUp() {
        employeeRepository = new EmployeeRepository();
        hrmService = new HRMService(employeeRepository);
    }

    @Test
    void shouldCalculateAverageSalary() {
        employeeRepository.save(new Employee("Ivan Ivanov", "Developer", 100.0, LocalDate.of(2024, 1, 10)));
        employeeRepository.save(new Employee("Petr Petrov", "QA", 200.0, LocalDate.of(2024, 2, 10)));
        employeeRepository.save(new Employee("Anna Sidorova", "Manager", 300.0, LocalDate.of(2024, 3, 10)));

        double averageSalary = hrmService.calculateAverageSalary();

        assertEquals(200.0, averageSalary);
    }

    @Test
    void shouldFindTopPaidEmployee() {
        employeeRepository.save(new Employee("Ivan Ivanov", "Developer", 100.0, LocalDate.of(2024, 1, 10)));
        employeeRepository.save(new Employee("Anna Sidorova", "Manager", 500.0, LocalDate.of(2024, 2, 10)));
        employeeRepository.save(new Employee("Petr Petrov", "QA", 300.0, LocalDate.of(2024, 3, 10)));

        Employee topEmployee = hrmService.findTopPaidEmployee();

        assertEquals("Anna Sidorova", topEmployee.getName());
        assertEquals(500.0, topEmployee.getSalary());
    }

    @Test
    void shouldReturnNullWhenNoEmployeesExist() {
        Employee topEmployee = hrmService.findTopPaidEmployee();

        assertNull(topEmployee);
    }

    @Test
    void shouldFindEmployeesByPosition() {
        employeeRepository.save(new Employee("Ivan Ivanov", "Developer", 100.0, LocalDate.of(2024, 1, 10)));
        employeeRepository.save(new Employee("Anna Sidorova", "Manager", 500.0, LocalDate.of(2024, 2, 10)));
        employeeRepository.save(new Employee("Petr Petrov", "Developer", 300.0, LocalDate.of(2024, 3, 10)));

        List<Employee> developers = hrmService.findByPosition("Developer");

        assertEquals(2, developers.size());
    }
}