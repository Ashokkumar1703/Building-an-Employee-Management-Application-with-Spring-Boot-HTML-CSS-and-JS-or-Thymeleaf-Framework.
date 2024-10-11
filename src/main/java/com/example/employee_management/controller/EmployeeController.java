package com.example.employee_management.controller;

import com.example.employee_management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

    // CRUD methods for employees (create, read, update, delete)
}