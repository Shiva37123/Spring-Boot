package com.ben.restcruddemo.dao;

import com.ben.restcruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();

    Employee findById(int id);

    Employee saveOrUpdate(Employee employee);

    void deleteById(int id);
}
