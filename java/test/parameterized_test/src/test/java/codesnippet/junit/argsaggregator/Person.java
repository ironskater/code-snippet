package codesnippet.junit.argsaggregator;

import codesnippet.junit.enums.Types;

public class Person
{
	private String firstName;
	private String lastName;
	private Types type;

	public Person() {}

	public Person(String firstName, String lastName, Types type)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.type = type;
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

	public Types getType() {
		return type;
	}

	public void setType(Types type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", type=" + type + "]";
	}
}