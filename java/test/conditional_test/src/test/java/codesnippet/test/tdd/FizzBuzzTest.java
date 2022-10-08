package codesnippet.test.tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FizzBuzzTest {

    // If number is divisible by 3, pring Fizz
    // If number is divisible by 5, pring Buzz
    // If number is divisible by 3 and 5, pring FizzBuzz
    // If number is NOT divisible by 3 or 5, pring the number

    @DisplayName("Divisible by 3")
    @Test
    @Order(1)
    @Disabled
    public void testForDivisibleByThree() {

        fail("only test in linux OS");
    }

    // Fourth, Add more test cases
    @DisplayName("Divisible by 5")
    @Test
    @Order(2)
    public void testForDivisibleByFive() {

        // First, we just write a fail case
        // fail("fail");

        // Second, we write a function to pass the test
        String expected = "Buzz";

        assertEquals(expected, FizzBuzz.compute(5), "Should return Buzz");
    }

    @DisplayName("Divisible by 3 and 5")
    @Test
    @Order(3)
    public void testForDivisibleByThreeAndFive() {

        String expected = "FizzBuzz";

        assertEquals(expected, FizzBuzz.compute(15), "Should return FizzBuzz");
    }

    @DisplayName("NOT Divisible by 3 and 5")
    @Test
    @Order(4)
    public void testForNotDivisibleByThreeAndFive() {

        String expected = "1";

        assertEquals(expected, FizzBuzz.compute(1), "Should return number 1");
    }

    // Sixth, add more different input value test case, so that it can increase test coverage
    @DisplayName("Testing with dataset")
    @ParameterizedTest(name = "inputValue={0}, expected={1}")
    @CsvFileSource(resources = "/fizz_buzz_test.csv")
    @Order(5)
    public void testByDataset(int inputValue, String expected) {

        assertEquals(expected, FizzBuzz.compute(inputValue));
    }
}
