package codesnippet.spring.rest.model;

public class StudentCriteria {

    private String firstName;
    private String lastName;

    private StudentCriteria() {}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "StudentCriteria [firstName=" + firstName + ", lastName=" + lastName + "]";
    }
}
