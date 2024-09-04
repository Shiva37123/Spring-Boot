package com.ben.cruddemo.dao;

import com.ben.cruddemo.entity.Instructor;
import com.ben.cruddemo.entity.InstructorDetail;

public interface AppDAO {
    void save(Instructor theInstructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorById(int id);

    InstructorDetail findInstructorDetailById(int id);
    void deleteInstructorDetailById(int id);
}
