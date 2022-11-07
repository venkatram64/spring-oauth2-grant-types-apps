package com.venkat.scwa.service;

import com.venkat.scwa.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeService {

    public List<Employee> getAll(){
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
