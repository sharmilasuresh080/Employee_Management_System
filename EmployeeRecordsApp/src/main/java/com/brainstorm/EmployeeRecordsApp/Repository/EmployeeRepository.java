package com.brainstorm.EmployeeRecordsApp.Repository;

import com.brainstorm.EmployeeRecordsApp.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // JpaRepository provides built-in methods like save(), findById(), findAll(), deleteById()
}
