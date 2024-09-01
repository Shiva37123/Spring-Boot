package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService theEmployeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        theEmployeeService=employeeService;
    }



    // add mapping for "/list"

    @GetMapping("/list")
    public String listEmployees(Model model){

        // get the employee from db
        List<Employee> employees = theEmployeeService.findAll();

        // add to the spring model
        model.addAttribute("employees", employees);

        return "employees/list-employees";

    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){
        Employee employee = new Employee();

        model.addAttribute("employee", employee);

        return "employees/employee-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int id, Model model){
        Employee employee = theEmployeeService.findById(id);

        model.addAttribute("employee", employee);

        return "employees/employee-form";
    }
    @GetMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam("employeeId") int id){
        theEmployeeService.deleteById(id);

        return "redirect:/employees/list";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        theEmployeeService.save(employee);

        return "redirect:/employees/list";
    }

}
