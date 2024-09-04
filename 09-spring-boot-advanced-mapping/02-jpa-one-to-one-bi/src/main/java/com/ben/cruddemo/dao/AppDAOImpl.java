package com.ben.cruddemo.dao;

import com.ben.cruddemo.entity.Instructor;
import com.ben.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AppDAOImpl implements AppDAO{

    //define field for entity manager

//    inject

    private EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }
    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        Instructor instructor = entityManager.find(Instructor.class, theId);

        return instructor;
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {

        // retrieve the instructor
        Instructor instructor=  findInstructorById(id);

        // delete the instructor
        entityManager.remove(instructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {

        return entityManager.find(InstructorDetail.class, id);

    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        // retrieve the instruction detail by id
        InstructorDetail instructorDetail = findInstructorDetailById(id);

        // remove the associated object reference
        instructorDetail.getInstructor().setInstructorDetail(null);

        // delete the instructor detail
        // NOTE: it will also delete the associated instruction since it is Cascade.ALL
        entityManager.remove(instructorDetail);
    }
}
