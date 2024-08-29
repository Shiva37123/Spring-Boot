package com.ben.cruddemo.dao;

import com.ben.cruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {

    void save(Student theStudent);

    Student findById(Integer id);

    List<Student> findAll();

    List<Student> findByLastName(String lastName);

    void update(Student student);

    void  delete(Integer id);

    int deleteAll();


}
