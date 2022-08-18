package codesnippet.spring.validation.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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

    @NotNull(message = "can''t be null")
    @Min(value = 0, message = "must be greater than or equal to 0")
    @Max(value = 10, message = "must be less than or equal to 10")
    private Integer freePasses;

    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "5 chars/digits only")
    private String postalCode;

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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Customer [firstName=" + firstName + ", freePasses=" + freePasses + ", lastName=" + lastName
                + ", postalCode=" + postalCode + "]";
    }
}
