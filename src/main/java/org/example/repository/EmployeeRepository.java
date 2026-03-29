package org.example.repository;

import org.example.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {
    private final List<Employee> employees = new ArrayList<>();
    private Long nextId = 1L;

    public Employee save(Employee employee) {
        employee.setId(nextId);
        nextId++;
        employees.add(employee);
        return employee;
    }

    public List<Employee> findAll() {
        return employees;
    }

    public Employee findById(Long id) {
        for (Employee employee : employees) {
            if (employee.getId().equals(id)) {
                return employee;
            }
        }
        return null;
    }

    public void delete(Long id) {
        Employee employee = findById(id);
        if (employee != null) {
            employees.remove(employee);
        }
    }
}