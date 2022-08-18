package codesnippet.spring.validation.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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

    @Min(value = 0, message = "must be greater than or equal to 0")
    @Max(value = 10, message = "must be less than or equal to 10")
    private int freePasses;

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

    public int getFreePasses() {
        return freePasses;
    }

    public void setFreePasses(int freePasses) {
        this.freePasses = freePasses;
    }

    @Override
    public String toString() {
        return "Customer [firstName=" + firstName + ", freePasses=" + freePasses + ", lastName=" + lastName + "]";
    }
}
