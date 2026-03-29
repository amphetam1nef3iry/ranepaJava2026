package org.example.service;

import org.example.model.Employee;
import org.example.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;

public class HRMService {
    private final EmployeeRepository employeeRepository;

    public HRMService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public double calculateAverageSalary() {
        List<Employee> employees = employeeRepository.findAll();

        if (employees.isEmpty()) {
            return 0;
        }

        double sum = 0;

        for (Employee employee : employees) {
            sum += employee.getSalary();
        }

        return sum / employees.size();
    }

    public Employee findTopPaidEmployee() {
        List<Employee> employees = employeeRepository.findAll();

        if (employees.isEmpty()) {
            return null;
        }

        Employee topEmployee = employees.get(0);

        for (Employee employee : employees) {
            if (employee.getSalary() > topEmployee.getSalary()) {
                topEmployee = employee;
            }
        }

        return topEmployee;
    }

    public List<Employee> findByPosition(String position) {
        List<Employee> employees = employeeRepository.findAll();
        List<Employee> filteredEmployees = new ArrayList<>();

        for (Employee employee : employees) {
            if (employee.getPosition().equalsIgnoreCase(position)) {
                filteredEmployees.add(employee);
            }
        }

        return filteredEmployees;
    }
}