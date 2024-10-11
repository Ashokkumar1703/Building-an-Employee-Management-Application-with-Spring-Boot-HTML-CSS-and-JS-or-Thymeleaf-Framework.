package com.example.employee_management.controller;

import com.example.employee_management.entity.Employee;
import com.example.employee_management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.employee_management.exception.ResourceNotFoundException;

import javax.validation.Valid;

@Controller
@RequestMapping("/employees")
public class EmployeeWebController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeWebController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Display list of employees
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "index";
    }
    

    // Show the form to add a new employee
    @GetMapping("/add-employee")
    public String showAddEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "add-employee";
    }

    @GetMapping("/employees/{id}")
    public String getEmployeeById(@PathVariable Long id, Model model) {
        Employee employee = employeeService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        model.addAttribute("employee", employee);
        return "employee-details";
    }
    
    // Save a new employee
    @PostMapping("/save-employee")
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            return "add-employee";
        }
        employeeService.saveEmployee(employee);
        return "redirect:/employees/";
    }

    // Show the form to edit an employee
    @GetMapping("/edit/{id}")
    public String showEditEmployeeForm(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id).orElse(null);
        model.addAttribute("employee", employee);
        return "edit-employee";
    }

    // Update an existing employee
    @PostMapping("/update-employee")
    public String updateEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            return "edit-employee";
        }
        employeeService.saveEmployee(employee);
        return "redirect:/employees/";
    }

    // Delete an employee
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployeeById(id);
        return "redirect:/employees/";
    }
}