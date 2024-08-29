package com.ben.restcontroller.rest;

import com.ben.restcontroller.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> students;

    //define @PostConstruct to load the data.... only once!
    @PostConstruct
    public void loadData(){
        students = new ArrayList<>();

        students.add(new Student("Poornima", "Patel"));
        students.add(new Student("Mario", "Rossi"));
        students.add(new Student("Mary", "Smith"));
    }

    // define endpoint for "/students" -return a list of students
    @GetMapping("/students")
    public List<Student> getStudents() {

        return students;
    }
 // define endpoint for "/student/id" -return a list of students
    @GetMapping("/student/{studentId}")
    public Student getStudents(@PathVariable int studentId) {

        if(studentId<0 || studentId>=students.size()){
            throw new StudentNotFoundException("Student id not found - " +studentId);
        }

        return students.get(studentId);
    }



}
