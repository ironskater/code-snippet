package codesnippet.spring.validation.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.PropertySource;

import codesnippet.spring.validation.annotation.CourseCode;

@PropertySource("classpath:messages.properties")
public class Customer
{
    private String firstName;

    @NotNull(message = "can''t be null")
    /**
     * Noted that whitespaces is also considered to pass this validation
     * So we need to trim the leading- and trailing-whitespaces from input fields
     * If a string only has whitespaces, trim it to null
     */
    @Size(min = 2, message = "at least 2 charactors")
    private String lastName;

    @CourseCode
    private String courseCode;

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

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    @Override
    public String toString() {
        return "Customer [courseCode=" + courseCode + ", firstName=" + firstName + ", lastName=" + lastName + "]";
    }
}
