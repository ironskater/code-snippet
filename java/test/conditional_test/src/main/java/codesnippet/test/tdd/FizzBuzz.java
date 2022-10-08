package codesnippet.test.tdd;

public class FizzBuzz {

    private FizzBuzz() {
    }

    // Third, start implementation and check whether it passes the test
    // Original Version
    // public static String compute(int ix) {

    //     if ((ix % 3 == 0) && (ix % 5 == 0)) {
    //         return "FizzBuzz";
    //     }

    //     if (ix % 3 == 0) {
    //         return "Fizz";
    //     } else if (ix % 5 == 0) {
    //         return "Buzz";
    //     }

    //     return Integer.toString(ix);
    // }

    // Fifth, we can start refactor
    public static String compute(int ix) {

        StringBuilder result = new StringBuilder();

        if (ix % 3 == 0) {
            result.append("Fizz");
        }

        if (ix % 5 == 0) {
            result.append("Buzz");
        }

        if (result.length() == 0) {
            result.append(ix);
        }

        return result.toString();
    }
}
