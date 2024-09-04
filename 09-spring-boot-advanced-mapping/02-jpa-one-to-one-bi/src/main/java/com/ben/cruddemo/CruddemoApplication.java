package com.ben.cruddemo;

import com.ben.cruddemo.dao.AppDAO;
import com.ben.cruddemo.entity.Instructor;
import com.ben.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner-> {
			// createInstructor(appDAO);

			// findInstructor(appDAO);
			
			//deleteInstructor(appDAO);

			// findInstructorDetail(appDAO);

			 deleteInstructorDetail(appDAO);
		};
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int id=3;
		System.out.println("Deleting instructor detail id: " + id);

		appDAO.deleteInstructorDetailById(id);

		System.out.println("Done deleting!");
	}

	private void findInstructorDetail(AppDAO appDAO) {

		// get the instructor details object
		int id = 2;
		InstructorDetail instructorDetail = appDAO.findInstructorDetailById(id);

		// print the instructor details

		System.out.println("Instructor Detail: " + instructorDetail);

		// print the associated instructor
		System.out.println("Instructor: " + instructorDetail.getInstructor());

	}

	private void deleteInstructor(AppDAO appDAO) {
		int id=1;
		System.out.println("Deleting instructor id: " + id);

		appDAO.deleteInstructorById(id);
	}

	private void findInstructor(AppDAO appDAO) {
		int id = 2;
		System.out.println("Finding instructor id: " + id);
		Instructor instructor = appDAO.findInstructorById(id);

		System.out.println("Instructor: "+instructor);

		System.out.println("The associated instructor Detail only: " + instructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
		//create Instructor object;
		Instructor tempInstructor = new Instructor("Shiv", "Ben", "ben@gmail.com");

		// create instructor detail object
		InstructorDetail instructorDetail = new InstructorDetail(
				"http://www.ben.com/youtube", "Coding");
		/*
		//create Instructor object;
		Instructor tempInstructor = new Instructor("Madhu", "Patel", "madhu@gmail.com");

		// create instructor detail object
		InstructorDetail instructorDetail = new InstructorDetail(
				"http://www.ben.com/youtube", "Guitar");
		*/
		// set instructor detail to instructor
		tempInstructor.setInstructorDetail(instructorDetail);

		// save instructor to database

		// NOTE: this will also save instructor detail object due to CascadeType.ALL
		System.out.println("Saving instructor..." + tempInstructor);

		appDAO.save(tempInstructor);
		System.out.println("Done");

	}
}
