package com.brainstorm.EmployeeRecordsApp.Controller;

import com.brainstorm.EmployeeRecordsApp.Dto.EmployeeDto;
import com.brainstorm.EmployeeRecordsApp.Service.EmployeeService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public EmployeeDto saveEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        return employeeService.createEmployee(employeeDto);
    }
 
    @GetMapping("/employeeCatalog")
    public List<EmployeeDto> employeesList() {
        return employeeService.getAllEmployee();
    }

    @GetMapping("/getEmployeeRecord/{id}")
    public EmployeeDto retrieveEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PatchMapping("/modifyEmployee/{id}")
    public EmployeeDto updateEmployee(@PathVariable Long id,@Valid @RequestBody EmployeeDto updatedEmployeeDto) {
        return employeeService.updateEmployeeById(id,updatedEmployeeDto);
    }
   
    @DeleteMapping("/deleteEmployee/{id}")
    public void removeEmployee(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
    }
    
}
