package codesnippet.spring.mvc.model;

import java.util.LinkedHashMap;

public class Student
{
	private String firstName;
	private String lastName;
	private String country;
	private LinkedHashMap<String, String> countryByOptions;

	public Student() {
		countryByOptions = new LinkedHashMap<>();

		// BR is actual value
		// Brazil is the label what user will see
		countryByOptions.put("BR", "Brazil");
		countryByOptions.put("FR", "France");
		countryByOptions.put("DE", "Germany");
		countryByOptions.put("IN", "India");
	}

	public Student(String firstName, String lastName, String country) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
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

	// when form is loaded, spring willl call student.getCountryOptions()
	public LinkedHashMap<String, String>
		getCountryByOptions() {
		return countryByOptions;
	}
}
