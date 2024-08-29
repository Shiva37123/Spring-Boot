package com.ben.thymeleafdemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {

    // need a controller method to show the initial HTML form

    @RequestMapping("/showForm")
    public String showForm(){
        return "helloworld-form";
    }

    // need a controller method to process the HTML form
    @RequestMapping("/processForm")
    public String processForm(){
        return "helloworld";
    }

    // A model to store data
    @RequestMapping("processForm2")
    public String giveShoutOut(HttpServletRequest request, Model model){
        String student = request.getParameter("studentName");

        student = student.toUpperCase();

        String result = "Yo! " + student;

        model.addAttribute("message", result);

        return "helloworld";
    }

    // A model to store data
    @RequestMapping("processForm3")
    public String processForm(@RequestParam("studentName") String student, Model model){

        student = student.toUpperCase();

        String result = "Yo Yo! " + student;

        model.addAttribute("message", result);

        return "helloworld";
    }

}
