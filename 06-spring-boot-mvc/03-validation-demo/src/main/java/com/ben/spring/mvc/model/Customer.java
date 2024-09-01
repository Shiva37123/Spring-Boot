package com.ben.spring.mvc.model;

import com.ben.spring.mvc.validation.CourseCode;
import jakarta.validation.constraints.*;

public class Customer {

    private String firstName;

    @NotNull(message = "is required")
//    @NotBlank(message = "is required")
    @Size(min=1, message = "is required")
    private String lastName;

    @NotNull(message = "is required")
    @Min(value = 0, message = "Must be greater than or equal to zero")
    @Max(value = 10, message = "Must be less than or equal to 10")
    private Integer freePasses;

    @Pattern(regexp = "^[a-zA-Z0-9]{5}" ,message = "only 5 chars/digits")
    private String postalCode;

    @CourseCode(value = "ABC", message = "Must start with ABC")
    private String courseCode;

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Integer getFreePasses() {
        return freePasses;
    }

    public void setFreePasses(Integer freePasses) {
        this.freePasses = freePasses;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
