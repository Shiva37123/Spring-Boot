package com.ben.thymeleafdemo.controller;

import com.ben.thymeleafdemo.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    @Value("${countries}")
    private List<String> countries;

    @Value("${languages}")
    private List<String> languages;

    @Value("${Systems}")
    private List<String> favSystem;

    @GetMapping("/showStudentForm")
    public String showForm(Model model){

        // Create student object
        Student student = new Student();

        //add student object to the model
        model.addAttribute("student", student);

        //add countries list to model
        model.addAttribute("countries", countries);
        model.addAttribute("languages", languages);
        model.addAttribute("Systems", favSystem);

        return "student-form";
    }

    @PostMapping("/processStudentForm")
    public String processStudentForm(@ModelAttribute("student") Student student){

        System.out.println("Student: "+student.getFirstName() +" " + student.getLastName());
        System.out.println("Country: " + student.getCountry());
        System.out.println("Language: " + student.getFavouriteLanguage());

        return "student-confirmation";
    }
}
