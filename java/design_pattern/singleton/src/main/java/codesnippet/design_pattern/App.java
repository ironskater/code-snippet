package codesnippet.design_pattern;

public class App
{
	public static void
		main( String[] args )
	{
        Thread thread1 = new Thread(() -> {
            System.out.println(Singleton.getInstance());
        });
        Thread thread2 = new Thread(() -> {
            System.out.println(Singleton.getInstance());
        });

        thread1.start();
        thread2.start();

        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();

        System.out.println("is singleton? " + (singleton1 == singleton2));

        Prototype prototype1 = new Prototype();
        Prototype prototype2 = new Prototype();

        System.out.println("is singleton? " + (prototype1 == prototype2));
	}
}
