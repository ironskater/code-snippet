package codesnippet.spring.mvc.model;

public class Student
{
    private String firstName;
    private String lastName;
    private String country;
    private String bloodType;

    public Student() {
        this("", "", "", "");
    }

    public Student(String firstName, String lastName, String country, String bloodType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.bloodType = bloodType;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    @Override
    public String toString() {
        return "Student [bloodType=" + bloodType + ", country=" + country + ", firstName=" + firstName + ", lastName="
                + lastName + "]";
    }
}
