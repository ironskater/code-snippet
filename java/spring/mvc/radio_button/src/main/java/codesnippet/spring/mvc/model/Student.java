package codesnippet.spring.mvc.model;

public class Student
{
    private String firstName;
    private String lastName;
    private String favoriteLanguage;

    public Student() {
        this("", "", "");
    }

    public Student(String firstName, String lastName, String favoriteLanguage) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.favoriteLanguage = favoriteLanguage;
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

    public String getFavoriteLanguage() {
        return favoriteLanguage;
    }

    public void setFavoriteLanguage(String favoriteLanguage) {
        this.favoriteLanguage = favoriteLanguage;
    }

    @Override
    public String toString() {
        return "Student [favoriteLanguage=" + favoriteLanguage + ", firstName=" + firstName + ", lastName=" + lastName
                + "]";
    }
}
