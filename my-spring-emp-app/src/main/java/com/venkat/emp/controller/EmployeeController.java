package com.venkat.emp.controller;

import com.venkat.emp.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private Environment environment;

    @GetMapping
    public List<Employee> getAll(){
        System.out.println("Port number " + environment.getProperty("local.server.port"));
        Employee employee = new Employee();
        employee.setFirstName("Venkatram");
        employee.setLastName("Veerareddy");
        employee.setDesignation("Sr. Developer");
        employee.setEmpNo("101");
        Employee employee1 = new Employee();

        employee1.setFirstName("Srijan");
        employee1.setLastName("Veerareddy");
        employee1.setDesignation("Developer");
        employee1.setEmpNo("100");

        return Arrays.asList(employee, employee1);
    }
}
