package com.ben.cruddemo;

import com.ben.cruddemo.dao.StudentDAO;
import com.ben.cruddemo.entity.Student;
import org.springframework.beans.factory.annotation.Qualifier;
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
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner ->{

			// createStudents(studentDAO);

			 createMultipleStudents(studentDAO);

			// readStudent(studentDAO);

			// queryForStudent(studentDAO);

			// queryForStudentByLastname(studentDAO);

			// updateStudent(studentDAO);

			// deleteStudent(studentDAO);

			// deleteAllStudent(studentDAO);
		};
	}

	private void deleteAllStudent(StudentDAO studentDAO) {

		System.out.println("Deleting all student");

		int numRowsDeleted = studentDAO.deleteAll();

		System.out.println("Deleted row count: " +numRowsDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int id = 3;
		System.out.println("deleting student with id: "+id);

		studentDAO.delete(id);
	}

	private void updateStudent(StudentDAO studentDAO) {

		// retrieve student based on the id: primary key
		int id = 1;
		System.out.println("Getting student with id: " + id);
		Student student = studentDAO.findById(id);

		//chane first name to "Scooby"
		System.out.println("Updating the student ...");
		student.setFirstName("Scooby");

		// update the student
		studentDAO.update(student);

		// display the updated student
		System.out.println("Updated student: " + student);
	}

	private void queryForStudentByLastname(StudentDAO studentDAO) {
		// get a list of students
		List<Student> students = studentDAO.findByLastName("Doe");

		//display list of student
		for(Student s: students){
			System.out.println(s);
		}
	}

	private void queryForStudent(StudentDAO studentDAO) {
		//get a list of student
		List<Student> students = studentDAO.findAll();

		//Display the list of student
		for(Student s: students){
			System.out.println(s);
		}

	}

	private void readStudent(StudentDAO studentDAO) {

		// create a student object
		System.out.println("Creating student Object...");
		Student tempStudent = new Student("anna", "Leo", "ana@gmail.com");

		//save the student
		System.out.println("Saving the student ...");
		studentDAO.save(tempStudent);

		//display id of the saved student
		System.out.println("Generated Id: " + tempStudent.getId());

		//retrieve student based on the id: primary key
		Student student = studentDAO.findById(tempStudent.getId());

		//display student
		System.out.println("Retrieved Student :"+ student.toString());

	}

	private void createMultipleStudents(StudentDAO studentDAO) {
//		TRUNCATE student_tracker.student
//		ALTER TABLE student_tracker.student AUTO_INCREMENT=4000
		//create the multiple student object
		System.out.println("Creating 3 new students object ...");
		Student tempStudent1 = new Student("Jhon", "Doe", "jhonl@gmail.com");
		Student tempStudent2 = new Student("Merry", "Public", "merry@gmail.com");
		Student tempStudent3 = new Student("Bonita", "Applebum", "boniti@gmail.com");

		//save the student object
		System.out.println("Saving the students ...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
	}

	private void createStudents(StudentDAO studentDAO) {

		//create the student object
		System.out.println("Creating new student object ...");
		Student tempStudent = new Student("Paul", "Doe", "paul@gmail.com");

		//save the student object
		System.out.println("Saving the student ...");
		studentDAO.save(tempStudent);

		//display id of the saved student
		System.out.println("Saved Student. Generated Id: "+ tempStudent.getId());

	}

}
