package com.venkat.student.controller;

import com.venkat.student.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private Environment environment;

    @GetMapping
    public List<Student> getAll(){
        System.out.println("Port number " + environment.getProperty("local.server.port"));
        Student student = new Student();
        student.setFirstName("Venkatram");
        student.setLastName("Veerareddy");
        student.setDescipline("PhD");
        student.setRollNo("200");

        Student student1 = new Student();
        student1.setFirstName("Srijan");
        student1.setLastName("Veerareddy");
        student1.setDescipline("MS");
        student1.setRollNo("201");
        return Arrays.asList(student, student1);
    }
}
