package com.brainstorm.EmployeeRecordsApp.Controller;

import com.brainstorm.EmployeeRecordsApp.Model.Employee;
import com.brainstorm.EmployeeRecordsApp.Service.EmployeeService;
import com.brainstorm.EmployeeRecordsApp.DTO.EmployeeDTO;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import jakarta.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/employees")
@Validated
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    
    @PostMapping("/addEmployee")
    public Employee saveEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    
    @GetMapping("/employeeCatalog")
    public List<Employee> employeesList() {
        return employeeService.getAllEmployee();
    }


    @GetMapping("/getEmployeeRecord/{id}")
    public Employee retrieveEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }


    @PutMapping("/modifyEmployee")
    public Employee updateEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.updateEmployeeById(employee);
    }
   
    @DeleteMapping("/deleteEmployee/{id}")
    public List<Employee> removeEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployeeById(id);
    }
    
}
