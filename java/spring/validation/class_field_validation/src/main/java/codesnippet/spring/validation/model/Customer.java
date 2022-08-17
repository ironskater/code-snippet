package codesnippet.spring.validation.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    @Override
    public String toString() {
        return "Customer [firstName=" + firstName + ", lastName=" + lastName + "]";
    }
}
