package snippetlab.java.design_pattern.decorator;

/**
 * Category: structural pattern
 *
 * Decorator pattern allows a user to add new functionality to an existing object without altering its structure.
 * This type of design pattern comes under structural pattern as this pattern acts as a wrapper to existing class.
 * This pattern creates a decorator class which wraps the original class and
 * provides additional functionality keeping class methods signature intact.
 *
 * https://www.tutorialspoint.com/design_pattern/decorator_pattern.htm
 */
public class App {

    public static void main( String[] args ) {

        Coffee simpleCoffee = new SimpleCoffee();
        Coffee milkCoffee = new MilkDecorator(simpleCoffee);
        Coffee sugarMilkCoffee = new SugarDecorator(milkCoffee);

        double simpleCoffeeCost = simpleCoffee.getCost();
        double milkCoffeeCost = milkCoffee.getCost();
        double sugarMilkCoffeeCost = sugarMilkCoffee.getCost();

        System.out.println(simpleCoffee.getDescription() + " $" + simpleCoffeeCost);
        System.out.println(milkCoffee.getDescription() + " $" + milkCoffeeCost);
        System.out.println(sugarMilkCoffee.getDescription() + " $" + sugarMilkCoffeeCost);
    }
}
