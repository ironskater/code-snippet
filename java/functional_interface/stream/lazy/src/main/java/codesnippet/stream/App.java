package codesnippet.stream;

import java.util.stream.IntStream;

public class App
{
	public static void
        main(String[] args)
	{
        IntStream stream = IntStream.range(1, 5);

        stream =  stream.peek(e -> System.out.println(e))
                        .filter(e -> {
                            // System.out.println(e);
                            if(e%2 == 0) {
                                System.out.println("filter: " + e);
                                return true;
                            } else {
                                return false;
                            }
                        })
                        .map(e -> {
                            int temp = e*e;
                            System.out.println("squre: " + temp);
                            return temp;
                        });

        System.out.println("invoking terminal operation");
        /**
         * Intermediate operations are lazy.
         * peek, filter, map will be invoked only when sum is executed
         */
        System.out.println("trigger termination operation, the sum is: " + stream.sum());
	}
}
