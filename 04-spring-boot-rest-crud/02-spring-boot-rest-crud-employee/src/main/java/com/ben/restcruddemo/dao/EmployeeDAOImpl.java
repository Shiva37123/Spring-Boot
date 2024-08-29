package com.ben.restcruddemo.dao;

import com.ben.restcruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    //define entity manager
    private EntityManager entityManager;

    //set up constructor injection
    @Autowired
    public EmployeeDAOImpl(EntityManager theEntityManager){
        entityManager=theEntityManager;
    }

    @Override
    public List<Employee> findAll() {

        // create query
        TypedQuery<Employee> query = entityManager.createQuery("from Employee", Employee.class) ;

        //execute query
        List<Employee> employees = query.getResultList();

        //return result
        return employees;
    }

    @Override
    public Employee findById(int id) {
//        TypedQuery<Employee> query = entityManager.createQuery("FROM Employee WHERE Id=:id");
        return  entityManager.find(Employee.class, id);
    }

    @Override
    public Employee saveOrUpdate(Employee employee) {
        return entityManager.merge(employee);
    }

    @Override
    public void deleteById(int id) {
        Employee employee = findById(id);
        entityManager.remove(employee);
    }
}
