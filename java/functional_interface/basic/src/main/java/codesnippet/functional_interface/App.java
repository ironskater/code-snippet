package codesnippet.functional_interface;

public class App
{
	@FunctionalInterface
	public static interface Calculator
	{
		int calculate(int a, int b);
	}

	public static void
		main( String[] args )
	{
		Calculator calculator = (a, b) -> add(a, b);

		System.out.println("add: " + calculator.calculate(3, 4));

		calculator = (a, b) -> multiply(a, b);

		System.out.println("multiply: " + calculator.calculate(3, 4));
	}

	private static int
		add(int a, int b)
	{
		return a + b;
	}

	private static int
		multiply(int a, int b)
	{
		return a * b;
	}
}
