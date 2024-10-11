package com.example.employee_management.service;

import com.example.employee_management.entity.Employee;
import com.example.employee_management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;

	@Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

	@Override
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeOptional(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee updateEmployeeDetails(Employee existingEmployee, Employee newDetails) {
        existingEmployee.setName(newDetails.getName());
        existingEmployee.setDepartment(newDetails.getDepartment());
        existingEmployee.setPosition(newDetails.getPosition());
        existingEmployee.setSalary(newDetails.getSalary());
        return employeeRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }
}