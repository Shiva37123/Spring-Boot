package com.ben.cruddemo;

import com.ben.cruddemo.dao.AppDAO;
import com.ben.cruddemo.entity.Course;
import com.ben.cruddemo.entity.Instructor;
import com.ben.cruddemo.entity.InstructorDetail;
import com.ben.cruddemo.entity.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner-> {

			// createCourseAndReview(appDAO);

			// retrieveCourseAndReviews(appDAO);

			deleteCourseAndReview(appDAO);

		};
	}

	private void deleteCourseAndReview(AppDAO appDAO) {
		int id = 10;

		System.out.println("Deleting course id: " + id);

		appDAO.deleteCourseById(id);

		System.out.println("Done!");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {

		// get the course and review
		int id = 10;
		Course course = appDAO.findCourseAndReviewsByCourseId(id);

		// print the course
		System.out.println("Course: \n"+course);

		// print the review
		System.out.println("Review: \n"+course.getReviews());

	}

	private void createCourseAndReview(AppDAO appDAO) {
		// create a course
		Course course = new Course("Pacman - How to score 1 million");

		// add reviews
		course.addReview(new Review("Great course ... loved it!"));
		course.addReview(new Review("Cool... course"));
		course.addReview(new Review("What a dumb course, you are an Idiot!"));

		// save the course
		System.out.println("Saving the course \n"+ course + "\nAnd Reviews\n" + course.getReviews());

		appDAO.saveCourse(course);

		System.out.println("Done!");

	}

	private void deleteCourseById(AppDAO appDAO) {
		int id = 10;
		System.out.println("Deleting course with id: "+ id);

		appDAO.deleteCourseById(id);

		System.out.println("Done! ");
	}

	private void updateCourse(AppDAO appDAO) {
		int courseId =  10;

		// find the course
		System.out.println("Finding course id: "+ courseId);
		Course course = appDAO.findCourseById(courseId);
		System.out.println("Course: \n"+course);

		System.out.println("Updating course...");
		course.setTitle("Enjoy the Simple Things");

		appDAO.updateCourse(course);
	}

	private void updateInstructor(AppDAO appDAO) {
		int id = 2;

		//find the instructor from the database
		System.out.println("Finding the instructor id: " + id);
		Instructor instructor = appDAO.findInstructorById(id);

		// update the instructor
		System.out.println("Updating Instructor: \n" + instructor);
		instructor.setLastName("TESTER");
		appDAO.updateInstructor(instructor);

		Instructor updatedInstructor = appDAO.findInstructorById(id);

		System.out.println("Updated instructor: \n"+updatedInstructor );
		System.out.println("Done! ");


	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {

		int id = 2;

		System.out.println("Finding instructor id: "+ id);
		Instructor instructor = appDAO.findInstructorByIdJoinFetch(id);

		System.out.println("Instructor: "+instructor);

		System.out.println("The associated Courses: "+ instructor.getCourses());

		System.out.println("Done! ");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int id= 2;
		System.out.println("Find instructor id: "+id);

		Instructor instructor = appDAO.findInstructorById(id);

		System.out.println("Instructor: " + instructor);

		// find courses for instructor

		System.out.println("Finding courses for instructor id: "+ id);

		List<Course> courses = appDAO.findCoursesByInstructorId(id);

		instructor.setCourses(courses);

		System.out.println("The associated courses: " + instructor.getCourses());

		System.out.println("Done!");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {

		// @OneToMany is by default to lazy--- to work with this make  fetch to EAGER

		//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "instructor",
		//		         cascade = {CascadeType.PERSIST, CascadeType.MERGE,
		//                            CascadeType.DETACH,CascadeType.REFRESH})
		int id= 2;
		System.out.println("Find instructor id: "+id);

		Instructor instructor = appDAO.findInstructorById(id);

		System.out.println("Instructor: " + instructor);



		System.out.println("The associated courses: " + instructor.getCourses());

		System.out.println("Done!");
	}

	private void createInstructorWithCourse(AppDAO appDAO) {
		//create Instructor object;
		Instructor tempInstructor = new Instructor("Susan", "Public", "susan@gmail.com");

		// create instructor detail object
		InstructorDetail instructorDetail = new InstructorDetail(
				"http://www.youtube.com/susan", "Video Games");

		tempInstructor.setInstructorDetail(instructorDetail);

		//create some courses
		Course course1 = new Course("Air Guitar - The Ultimate Guide");
		Course course2 = new Course("The Pinball Masterclass");

		// add courses to instructor

		tempInstructor.add(course1);
		tempInstructor.add(course2);

		// save the instructor to db
		// NOTE: this will also save the courses because of CascadeType.PERSIST

		System.out.println("Saving instructor: " + tempInstructor);
		System.out.println("The Courses: " + tempInstructor.getCourses());

		appDAO.save(tempInstructor);

		System.out.println("DONE!");


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
		int id=2;
		System.out.println("Deleting instructor id: " + id);

		appDAO.deleteInstructorById(id);

		System.out.println("Done!");
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
