package com.example.employee_management.service;

import com.example.employee_management.entity.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee saveEmployee(Employee employee);
    Optional<Employee> getEmployeeOptional(Long id);
    Employee getEmployeeById(Long id);
    Employee updateEmployeeDetails(Employee existingEmployee, Employee newDetails);
    void deleteEmployeeById(Long id);
	Optional<Employee> findById(Long id);
	    // Other methods...
	}
