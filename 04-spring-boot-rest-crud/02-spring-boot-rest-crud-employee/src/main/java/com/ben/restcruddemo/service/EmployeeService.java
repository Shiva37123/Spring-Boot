package com.ben.restcruddemo.service;

import com.ben.restcruddemo.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int id);

    Employee saveOrUpdate(Employee employee);

    void deleteById(int id);
}
