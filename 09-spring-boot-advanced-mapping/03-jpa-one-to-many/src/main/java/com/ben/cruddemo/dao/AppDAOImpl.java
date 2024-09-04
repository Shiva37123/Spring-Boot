package com.ben.cruddemo.dao;

import com.ben.cruddemo.entity.Course;
import com.ben.cruddemo.entity.Instructor;
import com.ben.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

        // get the courses
        List<Course> courses = instructor.getCourses();

        //break association of all courses for the instructor
        for(Course course: courses){
            course.setInstructor(null);
        }

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

    @Override
    public List<Course> findCoursesByInstructorId(int id) {

        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id = :data", Course.class);

        query.setParameter("data", id);

        List<Course> courses = query.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        // create query
        TypedQuery<Instructor> query = entityManager.createQuery(
                                "select i from Instructor i "
                                + "JOIN FETCH i.courses "
                                +"JOIN FETCH i.instructorDetail "
                                + "where i.id = :data", Instructor.class);
        // set parameter for query
        query.setParameter("data" , id);

        //execute query

        Instructor instructor = query.getSingleResult();
        return instructor;
    }

    @Override
    @Transactional
    public void updateInstructor(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void updateCourse(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where id= :data", Course.class);
        query.setParameter("data", id);

        Course course = query.getSingleResult();

        return course;
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        Course course = entityManager.find(Course.class, id);

        entityManager.remove(course);
    }
}
