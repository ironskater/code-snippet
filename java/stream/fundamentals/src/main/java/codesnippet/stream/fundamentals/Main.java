package codesnippet.stream.fundamentals;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main
{
	public static void main(String[] args)
	{
		List<Person> people = getPeople();

		// filter
		List<Person> females =
			people.stream()
				.filter(person -> person.getGender() == Gender.FEMALE)
				.collect(Collectors.toList());

		// females.forEach(System.out::println);

		// sort by age asc
		List<Person> sortAsc =
			people.stream()
				.sorted(Comparator.comparing(Person::getAge))
				.collect(Collectors.toList());

		// sortAsc.forEach(e -> {System.out.println(e);});

		// sort by age desc
		List<Person> sortDesc =
			people.stream()
				.sorted(Comparator.comparing(Person::getAge).reversed())
				.collect(Collectors.toList());

		// sortDesc.forEach(System.out::println);

		// sort age then gender
		List<Person> sortByAgeThenGender =
			people.stream()
				.sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getGender))
				.collect(Collectors.toList());

		// sortByAgeThenGender.forEach(System.out::println);

		// all match
		boolean allMatch =
			people.stream()
				.allMatch(person -> person.getAge() > 5);

		// System.out.println(allMatch); // It's true because every person's age is greater than 5

		// any match
		boolean anyMatch =
			people.stream()
				.anyMatch(person -> person.getAge() > 119);

		// System.out.println(anyMatch); // It's true because Zelda Brown fulfill this condition

		// none match
		boolean noneMatch =
			people.stream()
				.noneMatch(person -> "Hyde".equals(person.getName()));

		// System.out.println(noneMatch); // It's true because no one is called Hyde

		// max
		Optional<Person> max =
			people.stream()
				.max(Comparator.comparing(Person::getAge));

		// max.ifPresent(	person -> {
		// 					System.out.println(person);
		// 				});

		// min
		// people.stream()
		// 	.min(Comparator.comparing(Person::getAge))
		// 	.ifPresent(System.out::println);

		// group
		Map<Gender, List<Person>> peopleByGender =
			people.stream()
				.collect(Collectors.groupingBy(Person::getGender));

		peopleByGender.forEach((gender, persons) -> {
			System.out.println(gender);
			persons.forEach(person -> System.out.println(person));
			System.out.println();
		});
	}

	private static List<Person> getPeople()
	{
		return
			List.of(
				new Person("Antonio", 20, Gender.MALE),
				new Person("Alina Smith", 33, Gender.FEMALE),
				new Person("Power Smith", 33, Gender.MALE),
				new Person("Helen White", 57, Gender.FEMALE),
				new Person("Alex Boz", 14, Gender.MALE),
				new Person("Jamie Goa", 99, Gender.MALE),
				new Person("Anna Cook", 7, Gender.FEMALE),
				new Person("Zelda Brown", 120, Gender.FEMALE)
			);
	}
}
