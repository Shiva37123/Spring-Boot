package com.ben.restcruddemo.rest;

import com.ben.restcruddemo.entity.Employee;
import com.ben.restcruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    // quick and dirty: inject employee dao
    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService){
        employeeService = theEmployeeService;
    }

    //expose "/employees" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/employee/{employeeId}")
    public Employee findById(@PathVariable int employeeId){
        Employee employee = employeeService.findById(employeeId);
        if(employee==null)
            throw new RuntimeException("Employee id nt found - " + employeeId);
        return employee;
    }

    @PostMapping("/employee")
    public Employee addEmployee(@RequestBody Employee employee){
        //also ji=ust in case an id in json set it to 0

        employee.setId(0);

        Employee dbEmployee = employeeService.saveOrUpdate(employee);

        return dbEmployee;
    }

    @PutMapping("/employee")
    public Employee updateEmployee(@RequestBody Employee employee){

        Employee dbEmployee = employeeService.saveOrUpdate(employee);

        return dbEmployee;
    }


    @DeleteMapping("/employee/{employeeId}")
    public String deleteById(@PathVariable int employeeId){
        Employee employee = employeeService.findById(employeeId);

        if(employee==null){
            throw new RuntimeException("Employee is not found - "+ employeeId);
        }

        employeeService.deleteById(employeeId);

        return "Deleted employee id - " + employeeId;
    }

}
